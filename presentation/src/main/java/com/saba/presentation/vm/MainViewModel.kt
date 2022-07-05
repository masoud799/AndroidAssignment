package com.saba.presentation.vm

import android.os.Handler
import android.os.Looper
import android.text.Editable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.saba.base.BaseViewModel
import com.saba.common.Mapper
import com.saba.common.Resource
import com.saba.domain.entity.SearchEntityModel
import com.saba.domain.usecase.SearchUseCase
import com.saba.presentation.contract.MainContract
import com.saba.presentation.model.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchUseCase: SearchUseCase,
    private val searchMapper: Mapper<SearchEntityModel, SearchUiModel>
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }
    private lateinit var searchRunnable: Runnable

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            searchState = MainContract.SearchState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnSearchButtonClicked -> {
                val query = event.query
                searchQuery(query = query)
            }
        }
    }

    fun afterSearchQueryTextChanged(text: Editable?) {
        if (this::searchRunnable.isInitialized) {
            handler.removeCallbacks(searchRunnable)
        }
        searchRunnable = Runnable { checkSearchQuery(text) }
        handler.postDelayed(searchRunnable, 500)
    }

    private fun checkSearchQuery(text: Editable?) {
        if (text.isNullOrEmpty()) {
            setState { copy(searchState = MainContract.SearchState.Idle) }
            return
        }
        searchQuery(query = text.toString())
    }

    /**
     * Fetch search result
     */
    private fun searchQuery(query: String?) {
        viewModelScope.launch {
            searchUseCase.execute(query)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            // Set state
                            setState { copy(searchState = MainContract.SearchState.Loading) }
                        }
                        is Resource.Empty -> {
                            // Set state
                            setState { copy(searchState = MainContract.SearchState.Idle) }
                        }
                        is Resource.Success -> {
                            // Set state
                            val movies = it.data
                            if (movies.data.isNullOrEmpty()) {
                                setState { copy(searchState = MainContract.SearchState.NoResult) }
                            } else {
                                setState {
                                    copy(
                                        searchState = MainContract.SearchState.Success(
                                            searchMapper.from(i = movies)
                                        )
                                    )
                                }
                            }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        if (this::searchRunnable.isInitialized) {
            handler.removeCallbacks(searchRunnable)
        }
        super.onCleared()
    }
}
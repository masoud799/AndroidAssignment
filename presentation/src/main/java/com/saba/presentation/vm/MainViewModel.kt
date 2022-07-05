package com.saba.presentation.vm

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
    private val searchMapper : Mapper<SearchEntityModel, SearchUiModel>
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

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

    /**
     * Fetch Post Comments
     */
    private fun searchQuery(query : String?) {
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
                            setState { copy(searchState = MainContract.SearchState.Success(searchMapper.from(i = movies))) }
                        }
                        is Resource.Error -> {
                            // Set Effect
                            setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }
}
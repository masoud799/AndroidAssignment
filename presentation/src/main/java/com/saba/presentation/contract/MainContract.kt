package com.saba.presentation.contract

import com.saba.base.UiEffect
import com.saba.base.UiEvent
import com.saba.base.UiState
import com.saba.presentation.model.MovieUiModel

/**
 * Contract of Main Screen
 */
class MainContract {

    sealed class Event : UiEvent {
        data class OnSearchButtonClicked(val query : String) : Event()
    }

    data class State(
        val searchState: SearchState
    ) : UiState

    sealed class SearchState {
        object Idle : SearchState()
        object Loading : SearchState()
        data class Success(val movies : List<MovieUiModel>) : SearchState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

}
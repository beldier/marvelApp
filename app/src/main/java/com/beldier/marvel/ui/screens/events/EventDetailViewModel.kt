package com.beldier.marvel.ui.screens.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.repositories.EventsRepository
import com.beldier.marvel.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import  com.beldier.marvel.data.models.Result
/*
SavedStateHandle is passed automatically, no need for factory
 */
class EventDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel(){
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state  = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UIState(loading = true)
            _state.value = UIState(event = EventsRepository.find(id))
        }
    }


    data class UIState(
        val loading: Boolean = false,
        val event: Result<Event?> = Either.Right(null)
    )
}
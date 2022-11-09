package com.beldier.marvel.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.models.Result
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.data.repositories.EventsRepository
import com.beldier.marvel.ui.screens.characters.CharacterDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel:ViewModel() {

    private val _state  = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = UIState(loading = true)
            _state.value = UIState(items = EventsRepository.get())
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val items: Result<List<Event>> = emptyList<Event>().right()
    )
}
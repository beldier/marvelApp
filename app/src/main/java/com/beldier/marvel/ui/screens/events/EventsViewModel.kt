package com.beldier.marvel.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.data.repositories.EventsRepository
import kotlinx.coroutines.launch

class EventsViewModel:ViewModel() {

    var state by mutableStateOf(UIState())
        private set


    init {
        viewModelScope.launch {
            state = UIState(loading = true)
            state = UIState(items = EventsRepository.get())
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val items: List<Event> = emptyList()
    )
}
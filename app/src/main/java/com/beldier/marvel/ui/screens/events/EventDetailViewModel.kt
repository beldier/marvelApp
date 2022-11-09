package com.beldier.marvel.ui.screens.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.data.repositories.EventsRepository
import com.beldier.marvel.ui.navigation.NavArg
import kotlinx.coroutines.launch

/*
SavedStateHandle is passed automatically, no need for factory
 */
class EventDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel(){
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    var state by mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            state = UIState(loading = true)
            state = UIState(event = EventsRepository.find(id))
        }
    }


    data class UIState(
        val loading: Boolean = false,
        val event: Event? = null
    )
}
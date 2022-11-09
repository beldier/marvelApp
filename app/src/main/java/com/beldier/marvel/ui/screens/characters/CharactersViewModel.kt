package com.beldier.marvel.ui.screens.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.repositories.CharactersRepository
import kotlinx.coroutines.launch


class CharactersViewModel : ViewModel() {

    var state by mutableStateOf(UIState())
        private set


    init {
        viewModelScope.launch {
            state = UIState(loading = true)
            state = UIState(items = CharactersRepository.get())
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val items: List<com.beldier.marvel.data.models.Character> = emptyList()
    )
}
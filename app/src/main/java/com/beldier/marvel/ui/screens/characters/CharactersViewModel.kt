package com.beldier.marvel.ui.screens.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.repositories.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CharactersViewModel : ViewModel() {

    private val _state  = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = UIState(loading = true)
            _state.value = UIState(items = CharactersRepository.get())
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val items: List<com.beldier.marvel.data.models.Character> = emptyList()
    )
}
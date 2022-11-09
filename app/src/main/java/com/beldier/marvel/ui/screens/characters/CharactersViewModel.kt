package com.beldier.marvel.ui.screens.characters


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import com.beldier.marvel.data.models.Result
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
        val items: Result<List<com.beldier.marvel.data.models.Character>> = emptyList<com.beldier.marvel.data.models.Character>().right()
    )
}
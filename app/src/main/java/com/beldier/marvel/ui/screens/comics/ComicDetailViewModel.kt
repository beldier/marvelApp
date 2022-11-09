package com.beldier.marvel.ui.screens.comics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.models.Comic
import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.data.repositories.ComicsRepository
import com.beldier.marvel.data.repositories.EventsRepository
import com.beldier.marvel.ui.navigation.NavArg
import com.beldier.marvel.ui.screens.characters.CharacterDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
SavedStateHandle is passed automatically, no need for factory
 */
class ComicDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel(){
    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private val _state  = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UIState(loading = true)
            _state.value = UIState(comic = ComicsRepository.find(id))
        }
    }


    data class UIState(
        val loading: Boolean = false,
        val comic: Comic? = null
    )
}
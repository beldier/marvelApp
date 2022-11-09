package com.beldier.marvel.ui.screens.comics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.models.Comic
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.beldier.marvel.data.repositories.ComicsRepository

class ComicsViewModel : ViewModel() {

    val state = Comic.Format.values().associateWith { mutableStateOf(UIState()) }

    data class UIState(
        val loading: Boolean = false,
        val comics: List<Comic> = emptyList()
    )

    fun formatRequested(format : Comic.Format){
        var uiState = state.getValue(format)
        if(uiState.value.comics.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UIState(loading = true)
            uiState.value = UIState( comics = ComicsRepository.get(format))
        }
    }
}
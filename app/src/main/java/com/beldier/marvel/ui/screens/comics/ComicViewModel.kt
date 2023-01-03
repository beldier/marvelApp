package com.beldier.marvel.ui.screens.comics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beldier.marvel.data.models.Comic
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import arrow.core.right
import com.beldier.marvel.data.models.Result
import com.beldier.marvel.data.repositories.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val repository: ComicsRepository) : ViewModel() {

    val state = Comic.Format.values().associateWith { mutableStateOf(UIState()) }

    data class UIState(
        val loading: Boolean = false,
        val comics: Result<List<Comic>> = emptyList<Comic>().right()
    )

    fun formatRequested(format : Comic.Format){
        var uiState = state.getValue(format)
        if(uiState.value.comics.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = UIState(loading = true)
            uiState.value = UIState( comics = repository.get(format))
        }
    }
}
package com.beldier.marvel.ui.screens
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.ui.screens.common.MarvelItemDetailScreen
import com.beldier.marvel.ui.screens.common.MarvelItemsListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (com.beldier.marvel.data.models.Character) -> Unit) {
    var charactersState by remember { mutableStateOf(emptyList<com.beldier.marvel.data.models.Character>()) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }
    MarvelItemsListScreen(
        items = charactersState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(characterId: Int) {
    var characterState by remember { mutableStateOf<com.beldier.marvel.data.models.Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }
    characterState?.let {
        MarvelItemDetailScreen(it)
    }
}
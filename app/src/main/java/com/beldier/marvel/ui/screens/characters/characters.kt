package com.beldier.marvel.ui.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.ui.screens.common.MarvelItemDetailScreen
import com.beldier.marvel.ui.screens.common.MarvelItemsListScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(
    onClick: (com.beldier.marvel.data.models.Character) -> Unit,
    viewModel: CharactersViewModel = viewModel()
) {
    MarvelItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.items,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = viewModel()) {
    MarvelItemDetailScreen(loading = false, marvelItem = viewModel.state.character)

}
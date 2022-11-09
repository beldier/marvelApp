package com.beldier.marvel.ui.screens.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.repositories.EventsRepository
import com.beldier.marvel.ui.screens.common.MarvelItemDetailScreen
import com.beldier.marvel.ui.screens.common.MarvelItemsListScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventsViewModel = viewModel()) {
    MarvelItemsListScreen(
        items = viewModel.state.items,
        loading = viewModel.state.loading,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
    MarvelItemDetailScreen(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.event
    )
}

package com.beldier.marvel.ui.screens.common

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.beldier.marvel.data.models.MarvelItem
import com.beldier.marvel.data.models.Result
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsListScreen(
    loading: Boolean = false,
    items: Result<List<T>>,
    onClick: (T) -> Unit
) {
    items.fold({ ErrorMessage(it) }) { marvelItems ->
        var bottomSheetItem by remember { mutableStateOf<T?>(null) }
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        BackHandler {
            scope.launch { sheetState.hide() }
        }

        ModalBottomSheetLayout(
            sheetContent = {
                MarvelItemBottomPreview(
                    item = bottomSheetItem,
                    onGoToDetail = {
                        scope.launch {
                            sheetState.hide()
                            onClick(it)
                        }
                    }
                )
            },
            sheetState = sheetState
        ) {
            MarvelItemsList(
                loading = loading,
                items = marvelItems,
                onItemClick = onClick,
                onItemMore = {
                    bottomSheetItem = it
                    scope.launch { sheetState.show() }
                }
            )
        }
    }

}

@Composable
fun MyComposable(){
    val x = remember { mutableStateOf(0) }
    val y = remember { mutableStateOf(2) }

    // z an z2 do the same but underhodd z2 hold a memory space
    // while z only dependes on changes by x and y
    // use derivedstateof when operaction has an elevated cost
    val z = derivedStateOf { x.value + y.value }
    val z2 =  remember(x, y) { x.value + y.value}

}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsList(
    loading: Boolean,
    items: List<T>,
    onItemClick: (T) -> Unit,
    onItemMore: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading)
            CircularProgressIndicator()
        if (items.isNotEmpty()) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = modifier
            ) {
                items(items) {
                    MarvelListItem(
                        marvelItem = it,
                        onItemMore = onItemMore,
                        modifier = Modifier.clickable { onItemClick(it) }
                    )
                }
            }
        }
    }
}

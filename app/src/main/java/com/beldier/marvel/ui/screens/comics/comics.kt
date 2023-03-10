package com.beldier.marvel.ui.screens.comics

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.beldier.marvel.R
import com.beldier.marvel.data.models.Comic
import com.beldier.marvel.data.repositories.ComicsRepository
import com.beldier.marvel.ui.screens.common.MarvelItemDetailScreen
import com.beldier.marvel.ui.screens.common.MarvelItemsList
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beldier.marvel.ui.screens.common.ErrorMessage

@ExperimentalPagerApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit, viewModel: ComicsViewModel = hiltViewModel()) {

    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState()

    Column {
        ComicFormatsTabRow(
            pagerState = pagerState,
            formats = formats
        )
        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) { page ->
            val format = formats[page]
            viewModel.formatRequested(format)
            // to avoid usage of .value use by
            val pageState by viewModel.state.getValue(format)

            pageState.comics.fold({ErrorMessage(it)}){

                MarvelItemsList(
                    loading = pageState.loading,
                    items = it,
                    onItemClick = onClick,
                    onItemMore = {}
                )
            }
        }
    }

}

@ExperimentalPagerApi
@Composable
private fun ComicFormatsTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>
) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState,
                    tabPositions
                )
            )

        }
    ) {
        formats.forEach {
            Tab(
                selected = it.ordinal == pagerState.currentPage,
                onClick = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
                text = { Text(text = stringResource(it.toStringRes()).uppercase()) }
            )
        }
    }
}

@StringRes
private fun Comic.Format.toStringRes(): Int = when (this) {
    Comic.Format.COMIC -> R.string.comic
    Comic.Format.MAGAZINE -> R.string.magazine
    Comic.Format.TRADE_PAPERBACK -> R.string.trade_paperback
    Comic.Format.HARDCOVER -> R.string.hardcover
    Comic.Format.DIGEST -> R.string.digest
    Comic.Format.GRAPHIC_NOVEL -> R.string.graphic_novel
    Comic.Format.DIGITAL_COMIC -> R.string.digital_comic
    Comic.Format.INFINITE_COMIC -> R.string.infinite_comic
}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(viewModel: ComicDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.comic
    )

}
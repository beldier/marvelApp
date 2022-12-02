package com.beldier.marvel.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.beldier.marvel.R
import com.beldier.marvel.ui.navigation.*
import com.beldier.marvel.ui.theme.MarvelTheme
import com.beldier.marvel.ui.theme.RedDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Preview
@ExperimentalPagerApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MarvelApp() {
    val appState = rememberMarvelAppState()

    MarvelScreen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        if (appState.showUpNavigation) {
                            AppBarIcon(
                                imageVector = Icons.Default.ArrowBack,
                                onClick = { appState.onUpClick() })
                        } else {
                            AppBarIcon(
                                imageVector = Icons.Default.Menu,
                                onClick = { appState.onMenuClick() }
                            )
                        }
                    }
                )
            },
            bottomBar = {
                if (appState.showBottomNavigation) {
                    AppBottomNavigation(
                        bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                        currentRoute = appState.currentRoute,
                        onNavItemClick = {
                            appState.onNavItemClick(it)

                        })
                }
            },
            drawerContent = {
                DrawerContent(
                    drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                    selectedIndex = appState.drawerSelectedIndex,
                    onOptionClick = {
                        appState.onDrawerOptionClick(it)
                    }
                )
            },
            scaffoldState = appState.scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }
        SetStatusBarColorEffect()

    }
}

@Composable
fun SetStatusBarColorEffect(
    color: Color = MaterialTheme.colors.primaryVariant,
    systemUiController: SystemUiController = rememberSystemUiController()
){

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color
        )
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
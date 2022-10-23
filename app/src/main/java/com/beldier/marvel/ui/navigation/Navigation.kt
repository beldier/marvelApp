package com.beldier.marvel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beldier.marvel.ui.screens.CharactersScreen
import com.beldier.marvel.ui.screens.characterdetail.CharacterDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        composable(NavItem.Characters) {
            CharactersScreen(onClick = { character ->
                navController.navigate(NavItem.CharacterDetail.createNavRoute(character.id))
            })
        }

        composable(NavItem.CharacterDetail) {
            val id = it.findArg<Int>(NavArg.ItemId)
            CharacterDetailScreen(id = id, onUpClick = { navController.popBackStack() })
        }
    }
}


private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}
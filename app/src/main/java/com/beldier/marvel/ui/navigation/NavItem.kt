package com.beldier.marvel.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
){
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}"}
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }
    val args = navArgs.map {
        navArgument(it.key) { type = it.navType}
    }
    object Characters: NavItem("characters")
    object CharacterDetail: NavItem("characterDetail", listOf(NavArg.ItemId)){
        fun createNavRoute(itemId: Int) = "$baseRoute/$itemId"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)

}



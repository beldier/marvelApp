package com.beldier.marvel.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(

    internal val feature: Feature,
    val subRoute: String = "home",
    val navArgs: List<NavArg> = emptyList()
){

    class ContentType(feature: Feature) : NavItem(feature)

    class ContentDetail(feature: Feature): NavItem(feature,"detail", listOf(NavArg.ItemId)){
        fun createRoute(itemId: Int) = "${feature.route}/$subRoute/$itemId"
    }


    val route = run {
        val argKeys = navArgs.map { "{${it.key}}"}
        listOf(feature.route, subRoute)
            .plus(argKeys)
            .joinToString("/")
    }
    val args = navArgs.map {
        navArgument(it.key) { type = it.navType}
    }


}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)

}



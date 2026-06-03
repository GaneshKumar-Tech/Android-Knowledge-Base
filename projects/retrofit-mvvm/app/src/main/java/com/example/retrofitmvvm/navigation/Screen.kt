package com.example.retrofitmvvm.navigation

sealed class Screen(val route: String) {

    object Dashboard : Screen("dashboard")

    object Cart : Screen("cart")

    object Favorite : Screen("favorite")

}
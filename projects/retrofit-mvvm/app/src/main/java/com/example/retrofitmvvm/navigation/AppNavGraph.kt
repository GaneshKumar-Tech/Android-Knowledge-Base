package com.example.retrofitmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.retrofitmvvm.view.compose.cart.CartScreen
import com.example.retrofitmvvm.view.compose.dashboard.DashboardScreen
import com.example.retrofitmvvm.view.compose.favorite.FavoriteScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {

        composable(Screen.Dashboard.route) {
            DashboardScreen(navController)
        }

        composable(Screen.Cart.route) {
            CartScreen()
        }

        composable(Screen.Favorite.route) {
            FavoriteScreen()
        }
    }

}
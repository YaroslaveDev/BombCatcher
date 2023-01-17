package com.pfv.bombcatcher.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pfv.bombcatcher.ui.screens.game_screen.GameScreen
import com.pfv.bombcatcher.ui.screens.home_screen.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(
            route = Screens.HomeScreen.route
        ){
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.GameScreen.route
        ){
            GameScreen(
                navController = navController
            )
        }

    }


}
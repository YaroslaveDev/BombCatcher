package com.pfv.bombcatcher.ui.navigation

sealed class Screens(val route: String){
    object HomeScreen: Screens("home_screen")
    object GameScreen: Screens("game_screen")
    object AuthScreen: Screens("auth_screen")
    object LeadBoardScreen: Screens("lead_board_screen")
    object SettingsScreen: Screens("settings_screen")
}

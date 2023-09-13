package com.pfv.bombcatcher.ui.screens.home_screen.nav_state

sealed class HomeScreenNavState{

    object InitState: HomeScreenNavState()
    object NavToGameScreen: HomeScreenNavState()
}

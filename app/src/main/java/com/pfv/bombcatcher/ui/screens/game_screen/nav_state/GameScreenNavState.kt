package com.pfv.bombcatcher.ui.screens.game_screen.nav_state

sealed class GameScreenNavState{

    object InitState: GameScreenNavState()
    object OnBackNav: GameScreenNavState()
}

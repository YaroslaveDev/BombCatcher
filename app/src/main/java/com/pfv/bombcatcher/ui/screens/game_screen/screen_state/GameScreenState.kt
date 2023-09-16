package com.pfv.bombcatcher.ui.screens.game_screen.screen_state

sealed class GameScreenState{

    object InitState: GameScreenState()
    object GameOver: GameScreenState()
}

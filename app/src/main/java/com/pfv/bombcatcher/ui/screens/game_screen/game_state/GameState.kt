package com.pfv.bombcatcher.ui.screens.game_screen.game_state

sealed class GameState{

    object GameInProgress: GameState()
    object GameOver: GameState()
}

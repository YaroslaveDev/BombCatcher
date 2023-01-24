package com.pfv.bombcatcher.ui.screens.game_over_screen

sealed class GameOverScreenState{
    object InitialState: GameOverScreenState()
    object NewRecord: GameOverScreenState()
    object TopPlayer: GameOverScreenState()
    object LeadBoard: GameOverScreenState()
}

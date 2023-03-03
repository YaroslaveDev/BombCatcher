package com.pfv.bombcatcher.ui.screens.game_over_screen

sealed class GameOverScreenState{
    object BaseState: GameOverScreenState()
    object NewRecord: GameOverScreenState()
    object TopPlayer: GameOverScreenState()
    object LeadBoard: GameOverScreenState()
    object SetupState: GameOverScreenState()
}

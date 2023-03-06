package com.pfv.bombcatcher.ui.screens.game_over_screen

import com.pfv.bombcatcher.utils.TopPlayerPosition

sealed class GameOverScreenState{
    object InitialState: GameOverScreenState()
    object NewRecord: GameOverScreenState()
    data class TopPlayer(val position: TopPlayerPosition): GameOverScreenState()
    object SetupState: GameOverScreenState()
}

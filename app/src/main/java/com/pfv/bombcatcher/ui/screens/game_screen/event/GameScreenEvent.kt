package com.pfv.bombcatcher.ui.screens.game_screen.event

sealed class GameScreenEvent{
    object OnBackNav: GameScreenEvent()
    object OnShareClick: GameScreenEvent()
    object OnLeadBoardClick: GameScreenEvent()
    object SetGameOver: GameScreenEvent()
}

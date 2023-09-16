package com.pfv.bombcatcher.ui.screens.game_screen.ui_state

sealed class GameScreenUiState{

    object InitState: GameScreenUiState()
    object SetupState: GameScreenUiState()
    object ShareState: GameScreenUiState()
    object ShowLeadBoard: GameScreenUiState()
    object ShowAuthPopup: GameScreenUiState()
    object PauseState: GameScreenUiState()
}

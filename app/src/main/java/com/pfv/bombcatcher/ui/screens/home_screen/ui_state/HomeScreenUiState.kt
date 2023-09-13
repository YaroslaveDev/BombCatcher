package com.pfv.bombcatcher.ui.screens.home_screen.ui_state

sealed class HomeScreenUiState{

    object InitState: HomeScreenUiState()
    object ShowLeadBoard: HomeScreenUiState()
    object ShowSettings: HomeScreenUiState()
    object ShowAuthPopup: HomeScreenUiState()
}

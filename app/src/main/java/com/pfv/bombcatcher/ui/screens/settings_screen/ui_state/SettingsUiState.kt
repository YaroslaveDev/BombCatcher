package com.pfv.bombcatcher.ui.screens.settings_screen.ui_state

sealed class SettingsUiState{

    object InitState : SettingsUiState()
    object SetupState : SettingsUiState()
}

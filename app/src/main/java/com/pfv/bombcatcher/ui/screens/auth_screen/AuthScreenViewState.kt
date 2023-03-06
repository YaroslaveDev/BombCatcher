package com.pfv.bombcatcher.ui.screens.auth_screen

sealed class AuthScreenViewState{
    object Initial: AuthScreenViewState()
    object SetupState: AuthScreenViewState()
    object Success: AuthScreenViewState()
    data class Error( val message: String): AuthScreenViewState()
}

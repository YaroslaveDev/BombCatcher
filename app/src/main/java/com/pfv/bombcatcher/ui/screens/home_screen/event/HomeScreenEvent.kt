package com.pfv.bombcatcher.ui.screens.home_screen.event

sealed class HomeScreenEvent{

    object OnStartClick: HomeScreenEvent()
    object OnLeadBoardClick: HomeScreenEvent()
    object OnSettingsClick: HomeScreenEvent()
}

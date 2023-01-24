package com.pfv.bombcatcher.ui.screens.game_over_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameOverScreenViewModel @Inject constructor(): ViewModel() {

    var screenState by mutableStateOf<GameOverScreenState>(GameOverScreenState.InitialState)
}
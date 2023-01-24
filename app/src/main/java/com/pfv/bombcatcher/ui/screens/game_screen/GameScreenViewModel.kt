package com.pfv.bombcatcher.ui.screens.game_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(): ViewModel() {

    val isUserSignedIn by mutableStateOf( FirebaseAuth.getInstance().currentUser )
    var showAuthScreen by mutableStateOf( false )

}
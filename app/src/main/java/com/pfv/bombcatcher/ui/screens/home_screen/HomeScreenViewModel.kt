package com.pfv.bombcatcher.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel() {

    val isUserSignedIn = FirebaseAuth.getInstance().currentUser
    var showAuthScreen by mutableStateOf(false)
    var showLeadBoardScreen by mutableStateOf(false)
}
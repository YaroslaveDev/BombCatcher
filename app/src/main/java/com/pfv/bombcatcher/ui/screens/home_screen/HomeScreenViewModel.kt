package com.pfv.bombcatcher.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pfv.bombcatcher.ui.screens.home_screen.event.HomeScreenEvent
import com.pfv.bombcatcher.ui.screens.home_screen.nav_state.HomeScreenNavState
import com.pfv.bombcatcher.ui.screens.home_screen.ui_state.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel() {

    var uiState by mutableStateOf<HomeScreenUiState>(HomeScreenUiState.InitState)
    var navState by mutableStateOf<HomeScreenNavState>(HomeScreenNavState.InitState)

    val isUserSignedIn = FirebaseAuth.getInstance().currentUser
    var showAuthScreen by mutableStateOf(false)
    var showLeadBoardScreen by mutableStateOf(false)

    fun reduceEvent(event: HomeScreenEvent){
        when(event){
            HomeScreenEvent.OnLeadBoardClick -> {
                processLeadBoardClick()
            }
            HomeScreenEvent.OnSettingsClick -> {
                uiState = HomeScreenUiState.ShowSettings
            }
            HomeScreenEvent.OnStartClick -> {
                navState = HomeScreenNavState.NavToGameScreen
            }

            HomeScreenEvent.OnPrivacyPolicyClick -> {
                uiState = HomeScreenUiState.ShowPrivacyPolicyPopup
            }
            HomeScreenEvent.OnCustomizationClick -> {
                uiState = HomeScreenUiState.ShowCustomizationPopup
            }
        }
    }

    fun processLeadBoardClick(){
        if (isUserSignedIn != null) {
           uiState = HomeScreenUiState.ShowLeadBoard
        }else{
            uiState = HomeScreenUiState.ShowAuthPopup
        }
    }

    fun resetNavState(){
        navState = HomeScreenNavState.InitState
    }

    fun resetUiState(){
        uiState = HomeScreenUiState.InitState
    }
}
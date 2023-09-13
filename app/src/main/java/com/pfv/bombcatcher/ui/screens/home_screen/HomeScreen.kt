package com.pfv.bombcatcher.ui.screens.home_screen

import android.graphics.SurfaceTexture
import android.media.CamcorderProfile
import android.os.Build
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.home_screen.event.HomeScreenEvent
import com.pfv.bombcatcher.ui.screens.home_screen.nav_state.HomeScreenNavState
import com.pfv.bombcatcher.ui.screens.home_screen.ui_state.HomeScreenUiState
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    HomeScreenContent(
        viewModel
    )

    LaunchedEffect(viewModel.navState){
        when(viewModel.navState){
            HomeScreenNavState.InitState -> {}
            HomeScreenNavState.NavToGameScreen -> {
                navController.navigate(Screens.GameScreen.route)
                viewModel.resetNavState()
            }
        }
    }

    when(viewModel.uiState){
        HomeScreenUiState.InitState -> {

        }
        HomeScreenUiState.ShowLeadBoard -> {
            LeadBoardScreen {
                viewModel.resetUiState()
            }
        }
        HomeScreenUiState.ShowSettings -> {

        }

        HomeScreenUiState.ShowAuthPopup -> {
            AuthScreen(
                navController = navController,
                onDismiss = {
                    viewModel.resetUiState()
                },
                onAuthSuccess = {
                    viewModel.reduceEvent(HomeScreenEvent.OnLeadBoardClick)
                }
            )
        }
    }

}
package com.pfv.bombcatcher.ui.screens.game_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.bombcatcher.App
import kotlinx.coroutines.*
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.tools.createShareIntent
import com.pfv.bombcatcher.tools.screenHeight
import com.pfv.bombcatcher.tools.screenWidth
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.game_screen.components.PausePopup
import com.pfv.bombcatcher.ui.screens.game_screen.event.GameScreenEvent
import com.pfv.bombcatcher.ui.screens.game_screen.nav_state.GameScreenNavState
import com.pfv.bombcatcher.ui.screens.game_screen.ui_state.GameScreenUiState
import com.pfv.bombcatcher.ui.screens.home_screen.event.HomeScreenEvent
import com.pfv.bombcatcher.ui.theme.BaseGreenLight

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as Activity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseGreenLight),
    ) {
        Image(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.ic_city_large),
            contentDescription = "img"
        )
    }

    GameScreenContent(
        viewModel = viewModel,
        navController = navController
    )

    when(viewModel.uiState){
        GameScreenUiState.InitState -> {

        }
        GameScreenUiState.SetupState -> {

        }

        GameScreenUiState.ShareState -> {
            createShareIntent(activity)
        }

        GameScreenUiState.ShowAuthPopup -> {
            AuthScreen(
                navController = navController,
                onDismiss = {
                    viewModel.resetUiState()
                },
                onAuthSuccess = {
                    viewModel.reduceEvent(GameScreenEvent.OnLeadBoardClick)
                }
            )
        }
        GameScreenUiState.ShowLeadBoard -> {

        }

        GameScreenUiState.PauseState -> {
            PausePopup(
                onResumeGame = {
                    viewModel.resetUiState()
                }
            )
        }
    }

    LaunchedEffect(viewModel.navState){
        when(viewModel.navState){
            GameScreenNavState.InitState -> {

            }
            GameScreenNavState.OnBackNav -> {
                navController.navigateUp()
            }
        }
    }

}
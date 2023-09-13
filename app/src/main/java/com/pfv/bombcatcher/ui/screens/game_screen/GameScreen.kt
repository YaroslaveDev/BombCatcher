package com.pfv.bombcatcher.ui.screens.game_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.bombcatcher.App
import kotlinx.coroutines.*
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.tools.screenHeight
import com.pfv.bombcatcher.tools.screenWidth
import com.pfv.bombcatcher.ui.screens.game_screen.nav_state.GameScreenNavState
import com.pfv.bombcatcher.ui.screens.game_screen.ui_state.GameScreenUiState
import com.pfv.bombcatcher.ui.theme.BaseGreenLight

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {

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
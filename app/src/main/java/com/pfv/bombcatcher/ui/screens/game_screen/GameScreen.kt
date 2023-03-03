package com.pfv.bombcatcher.ui.screens.game_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.tools.screenHeight
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(viewModel.yPos) {

        if (viewModel.yPos < App.context.screenHeight - 90) {

            viewModel.yPos += viewModel.speed
        } else {
            viewModel.isGameOver = true
        }

        delay(10)
    }

    GameScreenContent(
        viewModel = viewModel,
        navController = navController
    )
}
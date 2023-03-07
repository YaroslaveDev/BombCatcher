package com.pfv.bombcatcher.ui.screens.game_screen

import android.annotation.SuppressLint
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
import kotlinx.coroutines.*
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.theme.BaseGreenLight

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {

//    var startTime by remember { mutableStateOf(0L) }
//    var endTime by remember { mutableStateOf(0L) }
//
//    startTime = endTime
//    endTime = SystemClock.elapsedRealtime()

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
}
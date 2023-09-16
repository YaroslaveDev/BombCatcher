package com.pfv.bombcatcher.ui.screens.lead_board_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.pfv.bombcatcher.ui.theme.Secondary
import java.util.*

@Composable
fun LeadBoardScreen(
    viewModel: LeadBoardViewModel = hiltViewModel(),
    onScreenClose: () -> Unit,
    onStartClick: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getAllGamersData()
    }

    Dialog(
        onDismissRequest = {

        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {

        when(viewModel.leadBoardScreenState){
            is LeadBoardScreenState.LoadingState -> {
                CircularProgressIndicator(
                    color = Secondary
                )
            }
            is LeadBoardScreenState.Success -> {
                LeadBoardScreenContent(
                    viewModel = viewModel,
                    onClick = onScreenClose,
                    onStartClick = onStartClick
                )
            }
        }
    }

    DisposableEffect(Unit){

        onDispose {
            viewModel.clearData()
            viewModel.addCloseable(onScreenClose)
        }
    }

}
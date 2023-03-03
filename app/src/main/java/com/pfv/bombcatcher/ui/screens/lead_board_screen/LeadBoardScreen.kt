package com.pfv.bombcatcher.ui.screens.lead_board_screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LeadBoardScreen(
    viewModel: LeadBoardViewModel = hiltViewModel()
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

        LeadBoardScreenContent(viewModel = viewModel)
    }
}
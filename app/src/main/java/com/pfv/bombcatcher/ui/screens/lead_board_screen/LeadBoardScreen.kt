package com.pfv.bombcatcher.ui.screens.lead_board_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.screens.lead_board_screen.list_item.LeaderBoardListItem
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

        LeadBoardScreenContent(
            viewModel = viewModel
        )
    }
}
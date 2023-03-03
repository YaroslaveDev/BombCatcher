package com.pfv.bombcatcher.ui.screens.lead_board_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.screens.lead_board_screen.list_item.LeaderBoardListItem
import java.util.*

@Composable
fun LeadBoardScreenContent(
    viewModel: LeadBoardViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.8f)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                modifier = Modifier.padding(vertical = 24.dp),
                text = stringResource(id = R.string.leader_board).uppercase(Locale.getDefault()),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                items(
                    items = viewModel.allGamersData
                ) {
                    LeaderBoardListItem(gamerData = it)
                }
            }
        }
    }
}
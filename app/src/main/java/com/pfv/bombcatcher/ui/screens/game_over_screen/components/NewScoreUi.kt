package com.pfv.bombcatcher.ui.screens.game_over_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.R

@Composable
fun NewScoreUi() {

    Image(
        painter = painterResource(id = R.drawable.ic_score_stars),
        contentDescription = "stars"
    )

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = stringResource(id = R.string.new_record),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}
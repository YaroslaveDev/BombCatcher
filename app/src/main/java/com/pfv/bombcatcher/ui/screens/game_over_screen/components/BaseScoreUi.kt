package com.pfv.bombcatcher.ui.screens.game_over_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.R

@Composable
fun BaseScoreUi(
    text: String
) {

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = stringResource(id = R.string.your_score),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    Text(
        modifier = Modifier.padding(bottom = 16.dp),
        text = text,
        fontSize = 46.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}
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
import com.pfv.bombcatcher.utils.TopPlayerPosition
import com.pfv.bombcatcher.utils.TopPlayerPosition.*

@Composable
fun TopPlayerUi(
    playerPosition: TopPlayerPosition,
    score: String
) {

    when(playerPosition){
        FIRST -> { PlayerPositionImg(R.drawable.ic_top_1_player, score) }
        SECOND -> { PlayerPositionImg(R.drawable.ic_top_2_player, score) }
        THIRD -> { PlayerPositionImg(R.drawable.ic_top_3_player, score) }
    }
}

@Composable
private fun PlayerPositionImg(img: Int, score: String){
    Image(
        painter = painterResource(id = img),
        contentDescription = "img"
    )

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = stringResource(id = R.string.new_record),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    Text(
        modifier = Modifier.padding(bottom = 16.dp),
        text = score,
        fontSize = 46.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}
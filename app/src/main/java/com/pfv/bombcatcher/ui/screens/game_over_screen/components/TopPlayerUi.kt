package com.pfv.bombcatcher.ui.screens.game_over_screen.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.utils.TopPlayerPosition
import com.pfv.bombcatcher.utils.TopPlayerPosition.*

@Composable
fun TopPlayerUi(
    playerPosition: TopPlayerPosition
) {

    when(playerPosition){
        FIRST -> { PlayerPositionImg(R.drawable.ic_first_place) }
        SECOND -> { PlayerPositionImg(R.drawable.ic_second_place) }
        THIRD -> { PlayerPositionImg(R.drawable.ic_third_place) }
        else -> {}
    }
}

@Composable
private fun PlayerPositionImg(img: Int){
    Image(
        painter = painterResource(id = img),
        contentDescription = "img"
    )
}
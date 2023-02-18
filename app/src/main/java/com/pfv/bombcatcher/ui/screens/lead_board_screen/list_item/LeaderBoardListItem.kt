package com.pfv.bombcatcher.ui.screens.lead_board_screen.list_item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.domain.model.GamerData

@Composable
fun LeaderBoardListItem(
    gamerData: GamerData
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.align(alignment = Alignment.Center),

        ) {

//            Image(
//                painter = ,
//                contentDescription = "img"
//            )

            Column {
                Text(
                    text = gamerData.firstName,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = gamerData.firstName,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
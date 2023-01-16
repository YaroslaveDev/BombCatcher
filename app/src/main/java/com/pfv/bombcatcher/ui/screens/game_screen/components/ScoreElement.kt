package com.pfv.bombcatcher.ui.screens.game_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn

@Composable
fun ScoreElement(
    score: String,
    modifier: Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp, start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RectangleBaseBtn(icon = R.drawable.ic_home)
        Surface(
            modifier = Modifier.fillMaxWidth(0.7f),
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier.padding().padding(vertical = 14.dp),
                    text = score,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        RectangleBaseBtn(icon = R.drawable.ic_settings)
    }
}
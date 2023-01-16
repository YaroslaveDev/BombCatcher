package com.pfv.bombcatcher.ui.base.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.ui.theme.Primary

@Composable
fun BaseShadowBtn(
    text: String
) {

    Box(
        modifier = Modifier
    ){
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(12.dp),
            color = Primary
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 56.dp, vertical = 14.dp),
                text = text,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}
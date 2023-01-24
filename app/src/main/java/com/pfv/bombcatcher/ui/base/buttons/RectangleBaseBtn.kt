package com.pfv.bombcatcher.ui.base.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun RectangleBaseBtn(
    icon: Int,
    color: Color = Secondary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .size(56.dp)
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(12.dp),

                )
            .clickable {
                onClick()
            },
        color = color,
        shape = RoundedCornerShape(12.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(id = icon),
                contentDescription = "img"
            )
        }

    }
}
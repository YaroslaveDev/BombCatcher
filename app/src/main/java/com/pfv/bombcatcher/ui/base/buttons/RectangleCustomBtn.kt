package com.pfv.bombcatcher.ui.base.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.tools.bounceClick
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun RectangleCustomBtn(
    icon: Int,
    color: Color = Secondary,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    onClick: () -> Unit,
) {

    Surface(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(12.dp),

                )
            .bounceClick()
            .clickable {
                onClick()
            },
        color = color,
        shape = RoundedCornerShape(12.dp),
        elevation = 20.dp
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(id = icon),
                contentDescription = "img",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

    }
}
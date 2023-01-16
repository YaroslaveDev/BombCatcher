package com.pfv.bombcatcher.ui.base.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun RectangleBaseBtn(
    icon: Int
) {

    Button(
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Secondary
        ),
        onClick = {}
    ) {

        Box(
            modifier = Modifier.size(30.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier,
                painter = painterResource(id = icon),
                contentDescription = "img"
            )
        }

    }

}
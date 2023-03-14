package com.pfv.bombcatcher.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.tools.CaptureBitmap

@Composable
fun ShareScoreUi(onBitmapReturn: (Bitmap) -> Unit) {

    CaptureBitmap(
        captureRequestKey = "",
        content = {
            Divider(
                modifier = Modifier
                    .size(200.dp)
                    .background(color = Color.Black)
            )
        },
        onBitmapCaptured = {
            onBitmapReturn(it)
        }

    )

}
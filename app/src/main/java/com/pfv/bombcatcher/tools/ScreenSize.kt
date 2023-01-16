package com.pfv.bombcatcher.tools

import android.content.Context
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.model.ScreenSizeDvo

fun Context.getScreenSize(density: Density, configuration: Configuration): ScreenSizeDvo {

    return ScreenSizeDvo(
        width = with(density) {configuration.screenWidthDp.dp.roundToPx()},
        height = with(density) {configuration.screenHeightDp.dp.roundToPx()}
    )
}
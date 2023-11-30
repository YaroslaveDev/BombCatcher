package com.pfv.bombcatcher.tools

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


fun vibrateEffect(context: Context, duration: Long = 100) {

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    val vibrationEffect = VibrationEffect.createOneShot(
        duration,
        VibrationEffect.DEFAULT_AMPLITUDE
    )
    vibrator.vibrate(vibrationEffect)
}
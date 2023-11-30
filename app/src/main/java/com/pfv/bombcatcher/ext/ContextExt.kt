package com.pfv.bombcatcher.ext

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

fun Context.getScreenSize(): Pair<Float, Float> {
    val metrics = DisplayMetrics()
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(metrics)

    return Pair(metrics.widthPixels.toFloat(), metrics.heightPixels.toFloat())
}
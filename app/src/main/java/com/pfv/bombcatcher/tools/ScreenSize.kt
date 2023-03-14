package com.pfv.bombcatcher.tools

import android.content.Context
import android.util.DisplayMetrics

val Context.screenWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight: Int
    get() = resources.displayMetrics.heightPixels * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

val Context.baseScreenHeight: Int
    get() = resources.displayMetrics.heightPixels

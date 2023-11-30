package com.pfv.bombcatcher.ext

import android.util.Range
import androidx.compose.ui.unit.Dp
import kotlin.random.Random

fun Range<Float>.getRandomValueFromRange() =
    Random.nextFloat() * (upper - lower) + lower

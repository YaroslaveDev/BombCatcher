package com.pfv.bombcatcher.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.pfv.bombcatcher.consts.AnimationDirection
import com.pfv.bombcatcher.consts.AnimationDirection.*

data class ScreenAnimationDvo(
    @DrawableRes
    val animatableObject: Int,
    val initPositionX: Float,
    val initPositionY: Float,
    val targetPositionX: Float,
    val targetPositionY: Float,
    val transitionDuration: Int = 2000,
    val animationDirection: AnimationDirection = TO_LEFT
){

    fun getPosToRestart() = when(animationDirection){
        TO_RIGHT -> targetPositionX - 20f
        TO_LEFT -> targetPositionX + 20f
        else -> 0f
    }

    fun getObjectMeasurements(context: Context): Pair<Float, Float> {
        val drawable: Drawable? = context.resources.getDrawable(animatableObject, context.theme)
        val width = drawable?.intrinsicWidth ?: 0
        val height = drawable?.intrinsicHeight ?: 0

        return Pair(width.toFloat(), height.toFloat())
    }


}

package com.pfv.bombcatcher.anim.home_screen_animations

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.consts.AnimationDirection
import com.pfv.bombcatcher.consts.AnimationDirection.*
import com.pfv.bombcatcher.model.ScreenAnimationDvo
import kotlin.math.roundToInt

@Composable
fun AnimatedImage(
    objectData: ScreenAnimationDvo,
    onAnimationEnd: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Animate X position
    val positionX by infiniteTransition.animateFloat(
        initialValue = objectData.initPositionX,
        targetValue = objectData.targetPositionX,
        animationSpec = infiniteRepeatable(
            animation = tween(objectData.transitionDuration, easing = LinearEasing),
        ), label = ""
    )

    // Animate Y position
    val positionY by infiniteTransition.animateFloat(
        initialValue = objectData.initPositionY,
        targetValue = objectData.targetPositionY,
        animationSpec = infiniteRepeatable(
            animation = tween(objectData.transitionDuration, easing = LinearEasing),
        ), label = ""
    )

    Image(
        painter = painterResource(id = objectData.animatableObject),
        contentDescription = null,
        modifier = Modifier
            .offset { IntOffset(positionX.roundToInt(), positionY.roundToInt()) }
    )

    LaunchedEffect(positionX) {

        Log.i("targexX", positionX.toString())
        Log.i("jjjjjjX", objectData.getPosToRestart().toString())

        when(objectData.animationDirection){
            TO_RIGHT -> {
                if (positionX >= objectData.getPosToRestart()) {
                    onAnimationEnd()
                }
            }
            TO_LEFT -> {
                if (positionX <= objectData.getPosToRestart()) {
                    onAnimationEnd()
                }
            }
            else -> {}
        }
    }

}
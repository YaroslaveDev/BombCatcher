package com.pfv.bombcatcher.consts

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.model.ScreenAnimationDvo

object DefaultScreenAnimations {

    val defaultAnimations = listOf(
        ScreenAnimationDvo(
            animatableObject = R.drawable.ic_plane_1,
            initPositionY = 0f,
            initPositionX = 0f,
            targetPositionY = 0f,
            targetPositionX = 0f,
            animationDirection = AnimationDirection.TO_LEFT

        ),
        ScreenAnimationDvo(
            animatableObject = R.drawable.plane_2,
            initPositionY = 0f,
            initPositionX = 0f,
            targetPositionY = 0f,
            targetPositionX = 0f,
            animationDirection = AnimationDirection.TO_LEFT

        ),
        ScreenAnimationDvo(
            animatableObject = R.drawable.ic_plane_3,
            initPositionY = 0f,
            initPositionX = 0f,
            targetPositionY = 0f,
            targetPositionX = 0f,
            animationDirection = AnimationDirection.TO_RIGHT

        ),
    )
}
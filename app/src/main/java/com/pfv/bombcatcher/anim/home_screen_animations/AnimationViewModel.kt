package com.pfv.bombcatcher.anim.home_screen_animations

import android.content.Context
import android.util.Range
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.consts.AnimationDirection
import com.pfv.bombcatcher.consts.AnimationDirection.*
import com.pfv.bombcatcher.consts.DefaultScreenAnimations
import com.pfv.bombcatcher.ext.getRandomValueFromRange
import com.pfv.bombcatcher.ext.getScreenSize
import com.pfv.bombcatcher.ext.randomIndex
import com.pfv.bombcatcher.model.ScreenAnimationDvo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimationViewModel @Inject constructor(): ViewModel() {

    var currentAnimObjectData by mutableStateOf(
        ScreenAnimationDvo(
            animatableObject = R.drawable.ic_plane_1,
            initPositionX = -300f,
            initPositionY = 100f,
            targetPositionX = 3000f,
            targetPositionY = 100f,
        )
    )

    fun updateAnimateItem(context: Context){

        val screenMeasurements = context.getScreenSize()
        val animateObjectMeasurements = currentAnimObjectData.getObjectMeasurements(context)
        val getRandomInitAnimateObject = DefaultScreenAnimations.defaultAnimations[DefaultScreenAnimations.defaultAnimations.randomIndex()]

        currentAnimObjectData = ScreenAnimationDvo(
            animatableObject = getRandomInitAnimateObject.animatableObject,
            initPositionX = when(getRandomInitAnimateObject.animationDirection){
                TO_RIGHT -> -animateObjectMeasurements.first
                TO_LEFT -> screenMeasurements.first + animateObjectMeasurements.first
                else -> 0f
            },
            initPositionY = Range(100f, screenMeasurements.second - 100f).getRandomValueFromRange(),
            targetPositionX = when(getRandomInitAnimateObject.animationDirection){
                TO_RIGHT -> screenMeasurements.first + animateObjectMeasurements.first
                TO_LEFT -> -animateObjectMeasurements.first
                else -> 0f
            },
            targetPositionY = Range(100f, screenMeasurements.second - 100f).getRandomValueFromRange(),
            animationDirection = getRandomInitAnimateObject.animationDirection
        )

    }


}
package com.pfv.bombcatcher.ui.screens.game_screen

import android.util.DisplayMetrics
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.tools.screenHeight
import com.pfv.bombcatcher.tools.screenWidth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor() : ViewModel() {

    var fallingTime by mutableStateOf(4f)
    var score by mutableStateOf(0)
    var isGameOver by mutableStateOf(false)
    var speed by mutableStateOf((App.context.screenHeight/(fallingTime * 100)))
    var xPos by mutableStateOf(Random.nextInt(90, App.context.screenWidth - 90))
    var yPos by mutableStateOf(-90)

    val isUserSignedIn by mutableStateOf(FirebaseAuth.getInstance().currentUser)
    var showAuthScreen by mutableStateOf(false)

    fun checkIsBombCatch(
        clickXPos: Int,
        clickYPos: Int,
        bombXPos: Int,
        bombYPos: Int,
        imgWidth: Int,
        imgHeight: Int
    ): Boolean {

        return (
                clickXPos > bombXPos && clickXPos < (bombXPos + imgWidth) && clickYPos > bombYPos && clickYPos < (bombYPos + imgHeight)
                )

    }

    fun gameSpeed(score: Int, speed: Int): Int {

        return if (score % 5 == 0 && score != 0) {
            fallingTime + 0.2
        } else speed
    }


}
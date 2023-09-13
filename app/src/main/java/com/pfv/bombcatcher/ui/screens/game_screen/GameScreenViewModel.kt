package com.pfv.bombcatcher.ui.screens.game_screen

import android.util.DisplayMetrics
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.tools.screenHeight
import com.pfv.bombcatcher.tools.screenWidth
import com.pfv.bombcatcher.ui.screens.game_screen.nav_state.GameScreenNavState
import com.pfv.bombcatcher.ui.screens.game_screen.ui_state.GameScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf<GameScreenUiState>(GameScreenUiState.InitState)
    var navState by mutableStateOf<GameScreenNavState>(GameScreenNavState.InitState)

    var defaultSpeed by mutableIntStateOf(App.context.screenHeight/800)
    var score by mutableIntStateOf(0)
    var isGameOver by mutableStateOf(false)
    var speed by mutableIntStateOf(App.context.screenHeight/800)
    var xPos by mutableIntStateOf(Random.nextInt(90, App.context.screenWidth - 90))
    var yPos by mutableFloatStateOf(-90f)

    var prevFallTime by mutableLongStateOf(0L)
    var currentFallTime by mutableLongStateOf(0L)

    val isUserSignedIn by mutableStateOf(FirebaseAuth.getInstance().currentUser)
    var showAuthScreen by mutableStateOf(false)

    fun checkIsBombCatch(
        clickXPos: Int,
        clickYPos: Int,
        bombXPos: Int,
        bombYPos: Float,
        imgWidth: Int,
        imgHeight: Int
    ): Boolean {

        return (clickXPos > bombXPos && clickXPos < (bombXPos + imgWidth) && clickYPos > bombYPos && clickYPos < (bombYPos + imgHeight))

    }

    fun gameSpeed(score: Int): Int {

        if (score % 5 == 0 && score != 0) {
            speed += 1
        }

        return speed
    }


}
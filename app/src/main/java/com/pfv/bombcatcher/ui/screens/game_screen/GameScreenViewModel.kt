package com.pfv.bombcatcher.ui.screens.game_screen

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
import com.pfv.bombcatcher.ui.screens.game_screen.event.GameScreenEvent
import com.pfv.bombcatcher.ui.screens.game_screen.game_state.GameState
import com.pfv.bombcatcher.ui.screens.game_screen.nav_state.GameScreenNavState
import com.pfv.bombcatcher.ui.screens.game_screen.screen_state.GameScreenState
import com.pfv.bombcatcher.ui.screens.game_screen.ui_state.GameScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf<GameScreenUiState>(GameScreenUiState.InitState)
    var navState by mutableStateOf<GameScreenNavState>(GameScreenNavState.InitState)
    var screenState by mutableStateOf<GameScreenState>(GameScreenState.InitState)
    var gameState by mutableStateOf<GameState>(GameState.GameInProgress)

    var defaultSpeed by mutableIntStateOf(App.context.screenHeight/800)
    var score by mutableIntStateOf(0)
    var speed by mutableIntStateOf(App.context.screenHeight/800)
    var xPos by mutableIntStateOf(Random.nextInt(90, App.context.screenWidth - 90))
    var yPos by mutableFloatStateOf(-90f)

    var prevFallTime by mutableLongStateOf(0L)
    var currentFallTime by mutableLongStateOf(0L)

    val isUserSignedIn by mutableStateOf(FirebaseAuth.getInstance().currentUser)

    fun reduceEvent(event: GameScreenEvent){
        when(event){
            GameScreenEvent.OnShareClick -> {
                uiState = GameScreenUiState.ShareState
            }

            GameScreenEvent.OnLeadBoardClick -> {
                processLeadBoardClick()
            }

            GameScreenEvent.OnBackNav -> {
                resetScreenState()
                resetUiState()
                navState = GameScreenNavState.OnBackNav
            }

            GameScreenEvent.SetGameOver -> {
                gameState = GameState.GameOver
                screenState = GameScreenState.GameOver
            }
        }
    }

    fun processLeadBoardClick(){
        if (isUserSignedIn != null) {
            uiState = GameScreenUiState.ShowLeadBoard
        }else{
            uiState = GameScreenUiState.ShowAuthPopup
        }
    }

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

    fun resetUiState(){
        uiState = GameScreenUiState.InitState
    }

    fun resetScreenState(){
        screenState = GameScreenState.InitState
    }

    fun resetGameState(){
        gameState = GameState.GameInProgress
    }

}
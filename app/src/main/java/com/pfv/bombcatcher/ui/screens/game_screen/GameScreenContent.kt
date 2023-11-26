package com.pfv.bombcatcher.ui.screens.game_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.SetSystemBarColors
import com.pfv.bombcatcher.anim.BoomAnimation
import com.pfv.bombcatcher.tools.baseScreenHeight
import com.pfv.bombcatcher.tools.createShareIntent
import com.pfv.bombcatcher.tools.screenHeight
import com.pfv.bombcatcher.tools.screenWidth
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreen
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState
import com.pfv.bombcatcher.ui.screens.game_screen.components.ScoreElement
import com.pfv.bombcatcher.ui.screens.game_screen.event.GameScreenEvent
import com.pfv.bombcatcher.ui.screens.game_screen.game_state.GameState
import com.pfv.bombcatcher.ui.screens.game_screen.screen_state.GameScreenState
import com.pfv.bombcatcher.ui.screens.game_screen.ui_state.GameScreenUiState
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import com.pfv.bombcatcher.ui.theme.Red_Inside_Shadow
import com.pfv.bombcatcher.ui.theme.Red_Outside_shadow
import kotlinx.coroutines.*
import kotlin.random.Random

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GameScreenContent(
    viewModel: GameScreenViewModel,
    navController: NavController
) {

    val vector = ImageVector.vectorResource(id = R.drawable.ic_bomb)
    val painter = rememberVectorPainter(image = vector)

    if (viewModel.gameState == GameState.GameInProgress &&  viewModel.uiState != GameScreenUiState.PauseState) {

        CoroutineScope(Dispatchers.Unconfined).launch {

            viewModel.currentFallTime = System.currentTimeMillis()

            if (viewModel.yPos < App.context.baseScreenHeight - 90) {

                if ((viewModel.currentFallTime - viewModel.prevFallTime) >= 10) {

                    viewModel.yPos += viewModel.speed
                    viewModel.prevFallTime = viewModel.currentFallTime
                }

            } else {
                viewModel.reduceEvent(GameScreenEvent.SetGameOver)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        MovableObject(
            viewModel = viewModel,
            vector = vector,
            painter = painter
        )

        ScoreElement(
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            score = viewModel.score.toString(),
            navigateToHome = {
                viewModel.reduceEvent(GameScreenEvent.OnBackNav)
            },
            onPauseClick = {
                viewModel.reduceEvent(GameScreenEvent.OnPauseClick)
            }
        )

        when(viewModel.screenState){
            GameScreenState.InitState -> {}
            GameScreenState.GameOver -> {
//                Image(
//                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
//                    painter = painterResource(id = R.drawable.ic_boom),
//                    contentDescription = "boom"
//                )



                GameOverScreen(
                    score = viewModel.score.toString(),
                    navHome = {
                        viewModel.reduceEvent(GameScreenEvent.OnBackNav)
                    },
                    restartGame = {
                        viewModel.resetGameState()
                        viewModel.resetScreenState()
                        viewModel.score = 0
                        viewModel.yPos = -90f
                        viewModel.speed = viewModel.defaultSpeed
                    },
                    onShare = {
                        viewModel.reduceEvent(GameScreenEvent.OnShareClick)
                    },
                    navLeadBoard = {
                        viewModel.reduceEvent(GameScreenEvent.OnLeadBoardClick)
                    },
                    navController = navController
                )

            }
            GameScreenState.GameOverAnimation -> {
                BoomAnimation(modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center)){
                    viewModel.screenState = GameScreenState.GameOver
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovableObject(
    viewModel: GameScreenViewModel,
    vector: ImageVector,
    painter: VectorPainter
) {
    if (viewModel.screenState == GameScreenState.InitState) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { tapOffset ->

                            if (viewModel.checkIsBombCatch(
                                    clickXPos = tapOffset.x.toInt(),
                                    clickYPos = tapOffset.y.toInt(),
                                    bombXPos = viewModel.xPos,
                                    bombYPos = viewModel.yPos,
                                    imgWidth = vector.defaultWidth.roundToPx(),
                                    imgHeight = vector.defaultHeight.roundToPx()
                                )
                            ) {
                                viewModel.score++
                                viewModel.yPos = -90f
                                viewModel.xPos = Random.nextInt(0, App.context.screenWidth - 180)
                                viewModel.speed = viewModel.gameSpeed(
                                    score = viewModel.score
                                )
                            }

                        }
                    )
                },
            contentDescription = "game_field",
        ) {

            translate(left = viewModel.xPos.toFloat(), top = viewModel.yPos) {
                with(painter) {
                    draw(
                        painter.intrinsicSize
                    )
                }
            }
        }
    }
}
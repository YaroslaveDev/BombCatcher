package com.pfv.bombcatcher.ui.screens.game_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.tools.screenHeight
import com.pfv.bombcatcher.tools.screenWidth
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreen
import com.pfv.bombcatcher.ui.screens.game_screen.components.ScoreElement
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreenContent(
    viewModel: GameScreenViewModel,
    navController: NavController
) {

    val vector = ImageVector.vectorResource(id = R.drawable.ic_bomb)
    val painter = rememberVectorPainter(image = vector)

    LaunchedEffect(viewModel.yPos) {

        if (viewModel.yPos < App.context.screenHeight - 90) {

            viewModel.yPos += viewModel.speed
        } else {
            viewModel.isGameOver = true
        }

        delay(10)
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
                navController.navigate(Screens.HomeScreen.route){
                    popUpTo(0)
                }
            }
        )

        if (viewModel.isGameOver){
            Image(
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.ic_boom),
                contentDescription = "boom"
            )
        }

        if (viewModel.isGameOver){
            GameOverScreen(
                score = viewModel.score.toString(),
                navHome = {
                    viewModel.isGameOver = false
                    navController.navigate(Screens.HomeScreen.route){
                        popUpTo(0)
                    }
                },
                restartGame = {
                    viewModel.isGameOver = false
                    viewModel.score = 0
                    viewModel.yPos = -90
                    viewModel.speed = App.context.screenHeight/400
                },
                onShare = {},
                navLeadBoard = {
                    if (viewModel.isUserSignedIn == null){
                        viewModel.showAuthScreen = true
                    }
                },
                navController = navController
            )
        }

        if (viewModel.showAuthScreen){
            AuthScreen(
                navController = navController,
                onDismiss = {}
            ){}
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovableObject(
    viewModel: GameScreenViewModel,
    vector: ImageVector,
    painter: VectorPainter
){
    if (!viewModel.isGameOver){
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
                                viewModel.yPos = -90
                                viewModel.xPos = Random.nextInt(0, App.context.screenWidth - 90)
                                viewModel.speed = viewModel.gameSpeed(
                                    score = viewModel.score,
                                    speed = viewModel.speed
                                )
                            }

                        }
                    )
                },
            contentDescription = "game_field",
        ) {

            translate(left = viewModel.xPos.toFloat(), top = viewModel.yPos.toFloat()) {
                with(painter) {
                    draw(
                        painter.intrinsicSize
                    )
                }
            }
        }
    }
}
package com.pfv.bombcatcher.ui.screens.game_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.tools.getScreenSize
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreen
import com.pfv.bombcatcher.ui.screens.game_screen.components.ScoreElement
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var score by rememberSaveable { mutableStateOf(0) }
    var isGameOver by remember { mutableStateOf(false) }

    val vector = ImageVector.vectorResource(id = R.drawable.ic_bomb)
    val painter = rememberVectorPainter(image = vector)
    val screenSize = context.getScreenSize(
        density = LocalDensity.current,
        configuration = LocalConfiguration.current
    )

    var speed by remember { mutableStateOf(10) }

    var xPos by rememberSaveable { mutableStateOf(Random.nextInt(90, screenSize.width - 90)) }
    var yPos by rememberSaveable { mutableStateOf(-90) }

    LaunchedEffect(yPos) {

        if (yPos < screenSize.height - 90) {

            yPos += speed


        }else{
            isGameOver = true
        }

        delay(10)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseGreenLight),
    ) {

        Image(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.ic_city_large),
            contentDescription = "img"
        )

        if (!isGameOver){
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { tapOffset ->

                                if (checkIsBombCatch(
                                        clickXPos = tapOffset.x.toInt(),
                                        clickYPos = tapOffset.y.toInt(),
                                        bombXPos = xPos,
                                        bombYPos = yPos,
                                        imgWidth = vector.defaultWidth.roundToPx(),
                                        imgHeight = vector.defaultHeight.roundToPx()
                                    )
                                ) {
                                    score++
                                    yPos = -90
                                    xPos = Random.nextInt(0, screenSize.width - 90)
                                    speed = gameSpeed(
                                        score = score,
                                        speed = speed
                                    )
                                }

                            }
                        )
                    },
                contentDescription = "game_field",
            ) {

                translate(left = xPos.toFloat(), top = yPos.toFloat()) {
                    with(painter) {
                        draw(
                            painter.intrinsicSize
                        )
                    }
                }
            }
        }

        ScoreElement(
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            score = score.toString(),
            navigateToHome = {
                navController.navigate(Screens.HomeScreen.route){
                    navController.popBackStack()
                }
            }
        )

        if (isGameOver){
            Image(
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.ic_boom),
                contentDescription = "boom"
            )
        }

        if (isGameOver){
            GameOverScreen(
                score = score.toString(),
                navHome = {
                    isGameOver = false
                    navController.navigate(Screens.HomeScreen.route){
                        navController.popBackStack()
                    }
                },
                restartGame = {
                    isGameOver = false
                    score = 0
                    yPos = -90
                    speed = 10
                },
                onShare = {},
                navLeadBoard = {
                    if (viewModel.isUserSignedIn != null){

                    }else{
                        viewModel.showAuthScreen = true
                    }
                }
            )
        }

        if (viewModel.showAuthScreen){
            AuthScreen(navController = navController)
        }

    }
}

private fun checkIsBombCatch(
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

private fun gameSpeed(score: Int, speed: Int): Int{

    return if (score % 5 == 0 && score != 0){
        speed + 2
    }
    else speed
}

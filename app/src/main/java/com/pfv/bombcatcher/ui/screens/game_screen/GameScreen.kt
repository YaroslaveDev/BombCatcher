package com.pfv.bombcatcher.ui.screens.game_screen

import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.tools.getScreenSize
import com.pfv.bombcatcher.ui.screens.game_screen.components.ScoreElement
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    navController: NavController
) {

    val vmContext = LocalContext.current as ViewModelStoreOwner
    val context = LocalContext.current

    val viewModel = ViewModelProvider(vmContext)[GameScreenViewModel::class.java]

    val vector = ImageVector.vectorResource(id = R.drawable.ic_bomb)
    val painter = rememberVectorPainter(image = vector)
    val screenSize = context.getScreenSize(density = LocalDensity.current, configuration = LocalConfiguration.current)

    var xPos by rememberSaveable { mutableStateOf( Random.nextInt(90,  screenSize.width - 90 )) }
    var yPos by rememberSaveable { mutableStateOf( -90 ) }

    LaunchedEffect(yPos){

        if (yPos >= screenSize.height - 90){
            yPos = -90
            xPos = Random.nextInt(0, screenSize.width - 90)
        }else{
            yPos += 10
        }
        delay(10)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseGreenLight),
    ){

        ScoreElement(
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            score = "0"
        )

        Image(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.ic_city_large),
            contentDescription = "img"
        )

        Canvas(
            modifier = Modifier.fillMaxSize(),
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


}
package com.pfv.bombcatcher.ui.screens.home_screen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.tools.CustomShadowButton
import com.pfv.bombcatcher.ui.base.buttons.BaseShadowBtn
import com.pfv.bombcatcher.ui.base.buttons.CustomLargeBtn
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.home_screen.HomeScreenViewModel
import com.pfv.bombcatcher.ui.screens.home_screen.event.HomeScreenEvent
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen
import com.pfv.bombcatcher.ui.theme.Primary
import com.pfv.bombcatcher.ui.theme.Primary_Shadow
import com.pfv.bombcatcher.ui.theme.Red_Inside_Shadow
import com.pfv.bombcatcher.ui.theme.Red_Outside_shadow
import com.pfv.bombcatcher.ui.theme.Secondary
import java.util.Locale

@Composable
fun HomeScreenLogo() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 170.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = 40.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo"
        )
    }
}

@Composable
fun HomeScreenLogoCustom(){

    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 20f,
        targetValue = -20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 170.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = 0.dp),
            painter = painterResource(id = R.drawable.img_big),
            contentDescription = "logo"
        )

        Row(
            modifier = Modifier
                .padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.img_b),
                contentDescription = "logo"
            )
            Image(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .offset(x = 4.dp)
                    .size(56.dp)
                    .graphicsLayer(
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0.0f,
                        ),
                        rotationZ = value
                    ),
                painter = painterResource(id = R.drawable.ic_bomb_logo),
                contentDescription = "logo"
            )
            Image(
                modifier = Modifier
                    .padding(top = 0.dp)
                    .size(56.dp)
                    .graphicsLayer(
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0.0f,
                        ),
                        rotationZ = value
                    ),
                painter = painterResource(id = R.drawable.ic_bomb_logo),
                contentDescription = "logo"
            )
            Image(
                modifier = Modifier
                    .padding(top = 0.dp),
                painter = painterResource(id = R.drawable.img_m),
                contentDescription = "logo"
            )
        }
    }

}

@Composable
fun HomeScreenActionBlock(
    viewModel: HomeScreenViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomLargeBtn(
            text = stringResource(id = R.string.start),
            modifier = Modifier,
            color = Primary,
            shadowColor = Red_Outside_shadow
        ){
            viewModel.reduceEvent(HomeScreenEvent.OnStartClick)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {

            RectangleBaseBtn(icon = R.drawable.ic_users_leadboard, size = 56.dp) {
                viewModel.reduceEvent(HomeScreenEvent.OnLeadBoardClick)
            }
//            Spacer(modifier = Modifier.width(10.dp))
//            RectangleBaseBtn(icon = R.drawable.ic_settings, size = 56.dp) {
//                viewModel.reduceEvent(HomeScreenEvent.OnSettingsClick)
//            }
            Spacer(modifier = Modifier.width(10.dp))
            RectangleBaseBtn(icon = R.drawable.ic_security, size = 56.dp) {
                viewModel.reduceEvent(HomeScreenEvent.OnPrivacyPolicyClick)
            }
        }
    }
}

@Composable
fun HomeScreenBackImage() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.home_screen_background),
            contentDescription = "Home_back",
            contentScale = ContentScale.Crop
        )
    }
}
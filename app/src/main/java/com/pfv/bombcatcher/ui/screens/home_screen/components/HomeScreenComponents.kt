package com.pfv.bombcatcher.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.base.buttons.BaseShadowBtn
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.home_screen.HomeScreenViewModel
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen

@Composable
fun HomeScreenLogo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(top = 40.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo"
        )
    }
}

@Composable
fun HomeScreenActionBlock(
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseShadowBtn(
            text = stringResource(id = R.string.start)
        ) {
            navController.navigate(Screens.GameScreen.route)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {

            RectangleBaseBtn(icon = R.drawable.ic_users_leadboard) {
                if (viewModel.isUserSignedIn != null) {
                    viewModel.showLeadBoardScreen = true
                }else{
                    viewModel.showAuthScreen = true
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            RectangleBaseBtn(icon = R.drawable.ic_settings) {

            }
        }
    }

    if (viewModel.showAuthScreen) {
        AuthScreen(
            navController = navController,
            onDismiss = {
                viewModel.showAuthScreen = false
            }
        ) {
            navController.navigate(Screens.HomeScreen.route)
            navController.popBackStack()
        }
    }

    if (viewModel.showLeadBoardScreen){
        LeadBoardScreen {
            viewModel.showLeadBoardScreen = false
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
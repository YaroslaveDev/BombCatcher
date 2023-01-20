package com.pfv.bombcatcher.ui.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenActionBlock
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenBackImage
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenLogo
import com.pfv.bombcatcher.ui.theme.BaseGreenLight

@Composable
fun HomeScreen(
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseGreenLight)
    ){

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ){
            HomeScreenLogo()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            HomeScreenBackImage()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            HomeScreenActionBlock(
                navController = navController,
            )
        }

    }

}
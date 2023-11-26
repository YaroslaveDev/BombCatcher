package com.pfv.bombcatcher.ui.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenActionBlock
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenBackImage
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenLogo
import com.pfv.bombcatcher.ui.screens.home_screen.components.HomeScreenLogoCustom
import com.pfv.bombcatcher.ui.theme.BaseGreenLight

@Composable
fun HomeScreenContent(
    viewModel: HomeScreenViewModel
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
            Column {

                HomeScreenLogo()
//                HomeScreenLogoCustom()
            }
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
                viewModel = viewModel
            )
        }

    }
}
package com.pfv.bombcatcher.ui.screens.home_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pfv.bombcatcher.anim.home_screen_animations.AnimatedImage
import com.pfv.bombcatcher.anim.home_screen_animations.AnimationViewModel

@Composable
fun HomeScreenAnimationView(
    animationViewModel: AnimationViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(Unit){
        animationViewModel.updateAnimateItem(context)
    }

    val currentObjectData by remember {
        derivedStateOf {
            animationViewModel.currentAnimObjectData
        }
    }

    AnimatedImage(
        objectData = currentObjectData,
        onAnimationEnd = {
            animationViewModel.updateAnimateItem(context)
        }
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize(),
    ){



    }
}
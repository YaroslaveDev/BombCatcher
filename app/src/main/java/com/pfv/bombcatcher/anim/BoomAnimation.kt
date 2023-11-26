package com.pfv.bombcatcher.anim

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pfv.bombcatcher.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BoomAnimation(modifier: Modifier, onFinish: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.big_boom_animation_2))

        LottieAnimation(
            modifier = modifier,
            composition = composition,
            iterations = 1,
            contentScale = ContentScale.Crop
        )

    LaunchedEffect(Unit){

        coroutineScope.launch {
            delay(900)
            onFinish()
        }
    }
}
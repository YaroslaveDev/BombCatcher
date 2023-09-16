package com.pfv.bombcatcher.ui.screens.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.pfv.bombcatcher.R
import kotlinx.coroutines.delay

@Composable
fun PausePopup(
    onResumeGame: () -> Unit
) {

    var downCounterToResume by remember {
        mutableIntStateOf(3)
    }
    var continueGame by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(continueGame) {
        if (continueGame) {
            while (downCounterToResume > 0) {
                delay(1000)
                downCounterToResume--
            }
            onResumeGame()
        }
    }

    Dialog(
        onDismissRequest = {  }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (!continueGame) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.game_paused),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    IconButton(
                        onClick = {
                            continueGame = true
                        }
                    ) {
                        Image(
                            modifier = Modifier
                                .size(60.dp),
                            painter = painterResource(id = R.drawable.ic_pause),
                            contentDescription = "img"
                        )
                    }
                }
            }else{
                Text(
                    text = downCounterToResume.toString(),
                    fontSize = 82.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
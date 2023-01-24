package com.pfv.bombcatcher.ui.screens.game_over_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.base.buttons.BaseShadowBtn
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState.*
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.BaseScoreUi
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.NewScoreUi
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.TopPlayerUi
import com.pfv.bombcatcher.ui.theme.BombCatcherTheme
import com.pfv.bombcatcher.ui.theme.Primary
import com.pfv.bombcatcher.ui.theme.Secondary
import com.pfv.bombcatcher.ui.theme.Typography
import com.pfv.bombcatcher.utils.TopPlayerPosition

@Composable
fun GameOverScreen(
    score: String,
    navHome: () -> Unit,
    restartGame: () -> Unit,
    onShare: () -> Unit,
    navLeadBoard: () -> Unit,
    viewModel: GameOverScreenViewModel = hiltViewModel()
) {



    Dialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .padding()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                when(viewModel.screenState){
                    is InitialState -> {
                        BaseScoreUi()
                    }
                    is LeadBoard -> {
                        //TopPlayerUi(playerPosition = TopPlayerPosition.FIRST)
                    }
                    is NewRecord -> {
                        NewScoreUi()
                    }
                    is TopPlayer -> {
                        //TopPlayerUi(playerPosition = )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_score_stars),
                    contentDescription = "stars"
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "YOUR NEW RECORD",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                )

                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = score,
                    fontSize = 46.sp,
                    lineHeight = 64.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    RectangleBaseBtn(icon = R.drawable.ic_home){
                        navHome()
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    RectangleBaseBtn(icon = R.drawable.ic_share){
                        onShare()
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    RectangleBaseBtn(icon = R.drawable.ic_restart, Primary){
                        restartGame()
                    }

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center

                ){
                    BaseShadowBtn(
                        text = "LEADERBOARD",
                        color = Secondary,
                    ) {
                        navLeadBoard()
                    }
                }
            }
        }
    }
}
package com.pfv.bombcatcher.ui.screens.game_over_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.ui.base.buttons.BaseShadowBtn
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState.*
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.BaseScoreUi
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.NewScoreUi
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen
import com.pfv.bombcatcher.ui.theme.Primary
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun GameOverScreen(
    score: String,
    navHome: () -> Unit,
    restartGame: () -> Unit,
    onShare: () -> Unit,
    navLeadBoard: () -> Unit,
    viewModel: GameOverScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val googleSignInInfo = GoogleSignIn.getLastSignedInAccount(context)

    LaunchedEffect(Unit){
        //viewModel.getAllGamersData()
        viewModel.getTask()
        viewModel.addGameData(
            GamerData(
                score = score.toInt(),
                firstName = googleSignInInfo?.givenName?:"null",
                lastName = googleSignInInfo?.familyName?:"null",
                userImg = googleSignInInfo?.photoUrl.toString()
            )
        )

        if ((viewModel.gamerData?.score ?: 0) < score.toInt()){
            viewModel.screenState = NewRecord
        }else{
            viewModel.screenState = InitialState
        }
    }

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
                        viewModel.showLeadBoardScreen = true
                    }
                }
            }
        }
    }

    if (viewModel.showLeadBoardScreen){
        LeadBoardScreen()
    }
}
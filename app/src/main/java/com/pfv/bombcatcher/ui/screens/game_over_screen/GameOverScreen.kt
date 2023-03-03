package com.pfv.bombcatcher.ui.screens.game_over_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState.InitialState
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState.NewRecord
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen

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
        GameOverScreenContent(
            viewModel = viewModel,
            score = score,
            navHome = { navHome() },
            restartGame = { restartGame() },
            onShare = { onShare() }) {

        }
    }

    if (viewModel.showLeadBoardScreen){
        LeadBoardScreen()
    }
}
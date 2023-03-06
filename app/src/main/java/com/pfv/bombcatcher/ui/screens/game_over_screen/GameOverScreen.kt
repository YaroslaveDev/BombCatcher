package com.pfv.bombcatcher.ui.screens.game_over_screen

import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState.NewRecord
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen

@Composable
fun GameOverScreen(
    score: String,
    navHome: () -> Unit,
    restartGame: () -> Unit,
    onShare: () -> Unit,
    navLeadBoard: () -> Unit,
    viewModel: GameOverScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val googleSignInInfo = GoogleSignIn.getLastSignedInAccount(context)

    LaunchedEffect(Unit){
        viewModel.getTask()
        viewModel.getAllGamersData(score)
        viewModel.addGameData(
            GamerData(
                score = score.toInt(),
                firstName = googleSignInInfo?.givenName.orEmpty(),
                lastName = googleSignInInfo?.familyName.orEmpty(),
                userImg = googleSignInInfo?.photoUrl.toString(),
                userId = Settings.Secure.ANDROID_ID
            )
        )
    }

    Dialog(onDismissRequest = {}) {

        GameOverScreenContent(
            viewModel = viewModel,
            score = score,
            navHome = { navHome() },
            restartGame = { restartGame() },
            onShare = { onShare() },
            navController = navController
        )
    }

    if (viewModel.showLeadBoardScreen){
        LeadBoardScreen{
            viewModel.showLeadBoardScreen = false

        }
    }
}
package com.pfv.bombcatcher.ui.screens.game_over_screen

import android.annotation.SuppressLint
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.screens.game_over_screen.GameOverScreenState.NewRecord
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreen

@SuppressLint("HardwareIds")
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
        if (viewModel.isUserSignedIn != null){
            viewModel.getTask()
            viewModel.getAllGamersData(score)
        }
        viewModel.addGameData(
            GamerData(
                score = score.toInt(),
                firstName = googleSignInInfo?.givenName.orEmpty(),
                lastName = googleSignInInfo?.familyName.orEmpty(),
                userImg = googleSignInInfo?.photoUrl.toString(),
                userId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            )
        )
    }

    Dialog(onDismissRequest = {}) {

        GameOverScreenContent(
            viewModel = viewModel,
            score = score,
            navHome = { navHome() },
            restartGame = { restartGame() },
            onShare = {
//                onShare()
                      },
            navController = navController
        )
    }

    if (viewModel.showLeadBoardScreen){
        LeadBoardScreen(
            onScreenClose = {
                viewModel.showLeadBoardScreen = false
            },
            onStartClick = {
                navController.navigate(Screens.GameScreen.route)
                viewModel.showLeadBoardScreen = false
            }
        )
    }
}
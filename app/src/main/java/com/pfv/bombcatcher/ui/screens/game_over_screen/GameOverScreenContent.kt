package com.pfv.bombcatcher.ui.screens.game_over_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.anim.CardWithAnimatedBorder
import com.pfv.bombcatcher.ui.base.buttons.BaseShadowBtn
import com.pfv.bombcatcher.ui.base.buttons.CustomLargeBtn
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn
import com.pfv.bombcatcher.ui.screens.auth_screen.AuthScreen
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.BaseScoreUi
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.NewScoreUi
import com.pfv.bombcatcher.ui.screens.game_over_screen.components.TopPlayerUi
import com.pfv.bombcatcher.ui.theme.Green_Outside_Shadow
import com.pfv.bombcatcher.ui.theme.Primary
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun GameOverScreenContent(
    viewModel: GameOverScreenViewModel,
    score: String,
    navHome: () -> Unit,
    restartGame: () -> Unit,
    onShare: () -> Unit,
    navController: NavController
) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CardWithAnimatedBorder(
                    startAnimation = (viewModel.screenState != GameOverScreenState.InitialState) && (viewModel.screenState != GameOverScreenState.SetupState),
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                                .padding()
                                .padding(vertical = 24.dp)
                                .animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {

                            when (viewModel.screenState) {
                                is GameOverScreenState.InitialState -> {
                                    BaseScoreUi(score)
                                }

                                is GameOverScreenState.NewRecord -> {
                                    NewScoreUi(score)
                                }

                                is GameOverScreenState.TopPlayer -> {
                                    TopPlayerUi(
                                        playerPosition = (viewModel.screenState as GameOverScreenState.TopPlayer).position,
                                        score
                                    )
                                }

                                is GameOverScreenState.SetupState -> {
                                    CircularProgressIndicator(
                                        modifier = Modifier.padding(vertical = 20.dp),
                                        color = Secondary
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                RectangleBaseBtn(icon = R.drawable.ic_home) {
                                    navHome()
                                }
//                                Spacer(modifier = Modifier.width(12.dp))
//                                RectangleBaseBtn(icon = R.drawable.ic_share) {
//                                    onShare()
//                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                RectangleBaseBtn(icon = R.drawable.ic_restart, Primary) {
                                    restartGame()
                                }

                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                contentAlignment = Alignment.Center

                            ) {

                                CustomLargeBtn(
                                    modifier = Modifier
                                        .padding(bottom = 4.dp),
                                    text = stringResource(id = R.string.leader_board).uppercase(),
                                    color = Secondary,
                                    shadowColor = Green_Outside_Shadow
                                ){
                                    signUpOrLeaderBoard(
                                        onShowLeadBoard = {
                                            viewModel.showLeadBoardScreen = true
                                        },
                                        onSignUp = {
                                            viewModel.showAuthScreen = true
                                        },
                                        viewModel = viewModel
                                    )
                                }
                            }
                        }
                    }
                )
            }

    if (viewModel.showAuthScreen){
        AuthScreen(
            navController = navController,
            onDismiss = {
                viewModel.showAuthScreen = false
            }
        ){
            viewModel.showAuthScreen = false
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearData()
            viewModel.addCloseable(restartGame)
        }
    }
}

private fun signUpOrLeaderBoard(
    viewModel: GameOverScreenViewModel,
    onShowLeadBoard: () -> Unit,
    onSignUp: () -> Unit
) {
    if (viewModel.isUserSignedIn != null) {
        onShowLeadBoard()
    } else {
        onSignUp()
    }
}
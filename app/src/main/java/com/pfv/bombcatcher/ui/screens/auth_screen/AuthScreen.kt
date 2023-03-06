package com.pfv.bombcatcher.ui.screens.auth_screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun AuthScreen(
    viewModel: AuthScreenViewModel = hiltViewModel(),
    navController: NavController,
    onDismiss: () -> Unit,
    onAuthSuccess: () -> Unit
) {

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                viewModel.signWithCredential(credential)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when (viewModel.authState.value) {
            is AuthScreenViewState.SetupState -> {
                CircularProgressIndicator(
                    color = Secondary
                )
            }
            is AuthScreenViewState.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screens.HomeScreen.route) {
                        navController.popBackStack()
                    }

                }
            }
            is AuthScreenViewState.Error -> {}

            is AuthScreenViewState.Initial -> {
                AuthScreenContent(
                    launcher = launcher,
                    navController = navController,
                    viewModel = viewModel,
                    onDismiss = onDismiss,
                    onAuthSuccess = onAuthSuccess
                )
            }
        }
    }
}

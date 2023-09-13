package com.pfv.bombcatcher.ui.screens.auth_screen

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.navigation.Screens
import java.util.*

@Composable
fun AuthScreenContent(
    viewModel: AuthScreenViewModel,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    navController: NavController,
    onDismiss: () -> Unit,
    onAuthSuccess: () -> Unit

) {

    val context = LocalContext.current
    val webClientId = stringResource(id = R.string.web_client_id)

    Dialog(onDismissRequest = {
        onDismiss()
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = stringResource(id = R.string.leader_board).uppercase(Locale.getDefault()),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,

                )

            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(id = R.string.please_sign_up),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(webClientId)
                        .requestEmail()
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    pressedElevation = 14.dp
                )
            )
            {
                Row(
                    modifier = Modifier
                        .padding()
                        .padding(vertical = 4.dp, horizontal = 32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "img"
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = stringResource(id = R.string.sign_up),
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
}
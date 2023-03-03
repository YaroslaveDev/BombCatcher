package com.pfv.bombcatcher.ui.screens.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    navController: NavController
) {

    val isUserSignedIn = FirebaseAuth.getInstance().currentUser

//    LaunchedEffect(Unit){
//        if (isUserSignedIn == null){
//            navController.navigate(Screens.AuthScreen.route)
//        }
//    }

    HomeScreenContent(navController)

}
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
import androidx.navigation.NavController
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.base.buttons.BaseShadowBtn
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn
import com.pfv.bombcatcher.ui.navigation.Screens
import com.pfv.bombcatcher.ui.theme.Primary
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun GameOverScreen(
    navController: NavController,
    score: String,
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
                    lineHeight = 24.sp
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
                        navController.navigate(Screens.HomeScreen.route){
                            navController.popBackStack()
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    RectangleBaseBtn(icon = R.drawable.ic_share){}
                    Spacer(modifier = Modifier.width(12.dp))
                    RectangleBaseBtn(icon = R.drawable.ic_restart, Primary){}

                }

                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    contentAlignment = Alignment.Center

                ){
                    BaseShadowBtn(
                        text = "LEADERBOARD",
                        color = Secondary,
                    ) {

                    }
                }
            }
        }
    }
}
package com.pfv.bombcatcher.ui.base.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.tools.bounceClick
import com.pfv.bombcatcher.ui.theme.Primary
import com.pfv.bombcatcher.ui.theme.Red_Inside_Shadow
import com.pfv.bombcatcher.ui.theme.Red_Outside_shadow

@Composable
fun BaseShadowBtn(
    text: String,
    color: Color = Primary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .size(width = 228.dp, height = 56.dp)
            .bounceClick(),
        colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = color
        ),
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp,

        )

    ) {
        Text(
            modifier = Modifier,
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun CustomLargeBtn(
    text: String,
    color: Color = Primary,
    shadowColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){

    Box(
        modifier = modifier
            .size(width = 228.dp, height = 56.dp)
            .bounceClick()
            .clickable {
                 onClick()
            },
    ){

        Spacer(
            modifier = Modifier
                .width(228.dp)
                .height(56.dp)
                .offset(x = -4.dp, y = 4.dp)
                .background(color = shadowColor, shape = RoundedCornerShape(12.dp))

            )

        Box(
            modifier = Modifier
                .width(228.dp)
                .height(56.dp)
                .background(color = color, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier,
                text = text,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }



//        Box(
//            modifier = Modifier
//                .width(172.dp)
//                .height(56.dp)
//                .background(Primary, shape = RoundedCornerShape(12.dp)),
//            contentAlignment = Alignment.Center
//        ){
//            Text(
//                modifier = Modifier,
//                text = "BUTTON",
//                color = Color.White,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
    }




}
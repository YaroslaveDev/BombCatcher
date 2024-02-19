package com.pfv.bombcatcher.ui.screens.customization_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun ItemToCustomize(
    modifier: Modifier,
    image: Int,
    text: Int
) {

    Column(
        modifier = modifier
            .background(color = BaseGreenLight, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(85.dp)
                .padding(top = 20.dp),
            painter = painterResource(id = image),
            contentDescription = "img"
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 20.dp),
            text = stringResource(id = text).uppercase(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }

}

@Preview
@Composable
fun ItemToCustomize_Preview() {
    ItemToCustomize(
        modifier = Modifier,
        image = R.drawable.ic_bomb_change_design,
        text = R.string.change_bomb
    )
}
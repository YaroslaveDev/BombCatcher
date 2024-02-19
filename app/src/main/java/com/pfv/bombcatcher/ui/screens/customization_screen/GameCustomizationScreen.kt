package com.pfv.bombcatcher.ui.screens.customization_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.screens.customization_screen.components.ItemToCustomize
import com.pfv.bombcatcher.ui.theme.Secondary
import java.util.Locale

@Composable
fun GameCustomizationScreen(
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.customization).uppercase(Locale.getDefault()),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 24.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ItemToCustomize(
                        modifier = Modifier,
                        image = R.drawable.ic_bomb_change_design,
                        text = R.string.change_bomb
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    ItemToCustomize(
                        modifier = Modifier,
                        image = R.drawable.ic_buildings_change_design,
                        text = R.string.change_view,

                    )
                }
            }

            Image(
                modifier = Modifier
                    .offset(x = 15.dp, y = -15.dp)
                    .background(color = Secondary, shape = RoundedCornerShape(6.dp))
                    .align(alignment = Alignment.TopEnd)
                    .size(40.dp)
                    .clickable {
                        onDismiss()
                    },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "img",
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }
    }
}
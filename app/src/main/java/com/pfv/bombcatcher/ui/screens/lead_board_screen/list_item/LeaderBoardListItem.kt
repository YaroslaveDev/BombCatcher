package com.pfv.bombcatcher.ui.screens.lead_board_screen.list_item

import android.annotation.SuppressLint
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import com.pfv.bombcatcher.ui.theme.Surface
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("HardwareIds")
@Composable
fun LeaderBoardListItem(
    gamerData: GamerData,
    index: Int
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (gamerData.userId == Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)) BaseGreenLight else Surface
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        if (index < 3) {
            Image(
                modifier = Modifier
                    .align(alignment = CenterVertically)
                    .size(34.dp),
                painter = painterResource(
                    id = when (index) {
                        0 -> R.drawable.ic_first_place
                        1 -> R.drawable.ic_second_place
                        else -> R.drawable.ic_third_place
                    }
                ),
                contentDescription = "img"
            )
        } else {
            Spacer(modifier = Modifier.width(34.dp))
        }

        Text(
            modifier = Modifier
                .align(alignment = CenterVertically)
                .padding(horizontal = 12.dp),
            text = (index + 1).toString(),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        GlideImage(
            modifier = Modifier.padding(end = 16.dp),
            imageModel = {
                gamerData.userImg
            },
            success = {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = RoundedCornerShape(160.dp)),
                    bitmap = it.imageBitmap!!,
                    contentDescription = "img",
                    contentScale = ContentScale.Crop
                )
            }
        )

        Column(
            modifier = Modifier
        ) {
            Text(
                text = gamerData.firstName,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = gamerData.lastName,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = gamerData.score.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            )
        }

    }
}
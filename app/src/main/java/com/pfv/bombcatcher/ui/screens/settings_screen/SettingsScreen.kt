package com.pfv.bombcatcher.ui.screens.settings_screen

import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.use_cases.GetUserData
import com.pfv.bombcatcher.ui.components.BaseAppToggleButton
import com.pfv.bombcatcher.ui.screens.lead_board_screen.components.EmptyLeadBoard
import com.pfv.bombcatcher.ui.screens.lead_board_screen.list_item.LeaderBoardListItem
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import com.pfv.bombcatcher.ui.theme.Secondary
import com.pfv.bombcatcher.ui.theme.Surface
import com.skydoves.landscapist.glide.GlideImage
import java.util.Locale

@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {

    LaunchedEffect(Unit){
        viewModel.getUserData()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.settings).uppercase(Locale.getDefault()),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 24.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(color = Surface),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    GlideImage(
                        modifier = Modifier.padding(end = 16.dp, top = 10.dp, bottom = 10.dp),
                        imageModel = {
                            viewModel.gamerData.userImg
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
                            text = viewModel.gamerData.firstName,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = viewModel.gamerData.lastName,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = Color.Black
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.mode),
                        fontSize = 14.sp,
                        color = Color.Black
                    )

//                    BaseAppToggleButton(
//                        currentSelection = ,
//                        toggleStates = ,
//                        onToggleChange = {}
//                    )
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
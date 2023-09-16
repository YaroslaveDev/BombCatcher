package com.pfv.bombcatcher.ui.screens.privacy_policy_screen

import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.ui.theme.Secondary
import com.pfv.bombcatcher.ui.theme.Surface
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun PrivacyPolicyScreen(
    onDismiss: () -> Unit
) {

    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Asset(R.raw.doc_privacy_policy),
        isZoomEnable = true
    )

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
                .background(color = Surface, shape = RoundedCornerShape(12.dp))
        ){
            VerticalPDFReader(
                state = pdfState,
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .fillMaxSize()
            )

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
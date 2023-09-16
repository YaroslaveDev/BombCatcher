package com.pfv.bombcatcher.tools

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ShareCompat
import com.pfv.bombcatcher.ui.components.ShareScoreUi

@Composable
fun createShareIntent(activity: Activity) {

    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    ShareScoreUi{
        val uri = bitmap?.let { it1 -> saveBitmapToFile(it1, activity) }
//        if (uri != null) {
//            val shareIntent = ShareCompat.IntentBuilder(activity)
//                .setType("image/png")  // Set exact mime type
//                .createChooserIntent()
//                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)  // Important for reading the file
//                .putExtra(Intent.EXTRA_STREAM, uri)
//
//            activity.startActivity(shareIntent)
//        }

        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            // Example: content://com.google.android.apps.photos.contentprovider/...
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpeg"
        }
        activity.startActivity(Intent.createChooser(shareIntent, null))
    }


}
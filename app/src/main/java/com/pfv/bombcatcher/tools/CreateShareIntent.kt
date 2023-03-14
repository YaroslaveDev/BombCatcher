package com.pfv.bombcatcher.tools

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.app.ShareCompat
import com.pfv.bombcatcher.ui.components.ShareScoreUi

@Composable
fun createShareIntent(activity: Activity) {

    ShareScoreUi{
        val bitmap = it

        val shareIntent = ShareCompat.IntentBuilder(activity)
            .setType("image/*")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)

        shareIntent.putExtra(Intent.EXTRA_STREAM, bitmap)

        activity.startActivity(shareIntent)
    }


}
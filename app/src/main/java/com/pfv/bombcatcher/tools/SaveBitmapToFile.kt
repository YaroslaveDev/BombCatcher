package com.pfv.bombcatcher.tools

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun saveBitmapToFile(bitmap: Bitmap, context: Context): Uri? {
    val file = File(context.cacheDir, "share_image_${System.currentTimeMillis()}.png")
    try {
        val outStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
}

package com.pfv.bombcatcher.tools

import android.content.Context
import android.media.MediaPlayer

fun playAudioFile(context: Context, audio: Int) {
    val mediaPlayer = MediaPlayer.create(context, audio)
    mediaPlayer.start()
}


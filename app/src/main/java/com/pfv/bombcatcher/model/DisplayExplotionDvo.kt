package com.pfv.bombcatcher.model

import androidx.compose.ui.geometry.Offset

data class DisplayExplotionDvo(
    val position: Offset,
    val needToDisplay: Boolean
)

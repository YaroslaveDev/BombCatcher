package com.pfv.bombcatcher.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.R
import com.pfv.bombcatcher.model.ToggleComponentDvo
import com.pfv.bombcatcher.ui.base.buttons.RectangleBaseBtn

@Composable
fun BaseToggleItem(
    item: ToggleComponentDvo,
    state: Boolean,
    onStateChanged: (Boolean) -> Unit
) {

    IconToggleButton(
        checked = state,
        onCheckedChange = onStateChanged,
    ) {

        RectangleBaseBtn(
            icon = item.image,
            size = 56.dp
        ) {}
    }
}

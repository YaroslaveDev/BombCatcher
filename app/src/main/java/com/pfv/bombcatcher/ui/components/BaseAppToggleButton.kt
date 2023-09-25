package com.pfv.bombcatcher.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pfv.bombcatcher.ui.theme.Green_Outside_Shadow
import com.pfv.bombcatcher.ui.theme.Secondary

@Composable
fun BaseAppToggleButton(
    currentSelection: Int,
    toggleStates: List<Int>,
    onToggleChange: (Int) -> Unit
) {

    val selectedTint = Secondary
    val unselectedTint = Green_Outside_Shadow

    Row(modifier = Modifier
        .height(IntrinsicSize.Min)
        .border(BorderStroke(1.dp, Color.LightGray))) {
        toggleStates.forEachIndexed { index, toggleState ->
            val isSelected = currentSelection == toggleState
            val backgroundTint = if (isSelected) selectedTint else unselectedTint

            if (index != 0) {
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
            }

            Row(
                modifier = Modifier
                    .background(backgroundTint)
                    .padding(vertical = 6.dp, horizontal = 8.dp)
                    .toggleable(
                        value = isSelected,
                        enabled = true,
                        onValueChange = { selected ->
                            if (selected) {
                                onToggleChange(toggleState)
                            }
                        })
            ) {

                Image(
                    painter = painterResource(id = toggleState),
                    contentDescription = "img"
                )

//                Text(toggleState.toCapital(), color = textColor, modifier = Modifier.padding(4.dp))
            }

        }
    }
}
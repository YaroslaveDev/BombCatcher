package com.pfv.bombcatcher

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pfv.bombcatcher.ui.navigation.AppNavigation
import com.pfv.bombcatcher.ui.theme.BaseGreenLight
import com.pfv.bombcatcher.ui.theme.BombCatcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BombCatcherTheme(
                darkTheme = false
            ) {
                Box(Modifier.safeDrawingPadding()) {
                    AppNavigation()
                }

            }

            SetSystemBarColors(
                color = BaseGreenLight,
                isDarkModeEnabled = false
            )
        }
    }
}

@Composable
fun SetSystemBarColors(color: Color, isDarkModeEnabled: Boolean) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = isDarkModeEnabled
        )

        systemUiController.setNavigationBarColor(
            color = color,
            darkIcons = isDarkModeEnabled
        )


        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = isDarkModeEnabled
        )
    }
}

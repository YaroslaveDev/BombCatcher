package com.pfv.bombcatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pfv.bombcatcher.ui.navigation.AppNavigation
import com.pfv.bombcatcher.ui.theme.BombCatcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BombCatcherTheme(
                darkTheme = false
            ) {
                AppNavigation()
            }
        }
    }
}

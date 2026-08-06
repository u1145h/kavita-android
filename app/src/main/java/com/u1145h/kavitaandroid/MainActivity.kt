package com.u1145h.kavitaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.u1145h.kavitaandroid.ui.KavitaApp
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity architecture. All UI is Compose.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            KavitaApp()
        }
    }
}

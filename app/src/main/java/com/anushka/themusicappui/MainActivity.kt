package com.anushka.themusicappui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import com.anushka.themusicappui.ui.MainView
import com.anushka.themusicappui.ui.theme.TheMusicAppUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheMusicAppUiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                     TheMusicAppUi(modifier = Modifier.padding(innerPadding)) {
                        MainView()
                    }
                }
            }

        }
    }
}
@Composable
fun TheMusicAppUi(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier) {
        content()
    }
}



package com.melisa.paradise.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.melisa.paradise.di.EdenApplication
import com.melisa.paradise.ui.screens.EdenApp
import com.melisa.paradise.ui.theme.EdenAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as EdenApplication).appContainer
        setContent {
            ProvideWindowInsets {
                EdenAppTheme {
                    Surface {
                        EdenApp(appContainer)
                    }
                }
            }
        }
    }
}
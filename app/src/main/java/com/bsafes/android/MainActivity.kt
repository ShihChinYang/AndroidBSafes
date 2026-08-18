package com.bsafes.android

import android.app.ActivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bsafes.android.ui.theme.BSafesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getString(R.string.product_variable_name).isNotEmpty())
            setTaskDescription(ActivityManager.TaskDescription(getString(R.string.app_name)))
        setContent {
            BSafesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MyWebViewComposable()
                }
            }
        }
        deleteTemporaryMediaFiles(this)
    }
}

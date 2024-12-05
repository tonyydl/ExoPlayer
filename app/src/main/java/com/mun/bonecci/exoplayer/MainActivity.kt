package com.mun.bonecci.exoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mun.bonecci.exoplayer.data.VideoMockData
import com.mun.bonecci.exoplayer.ui.VideoPlayerScreen
import com.mun.bonecci.exoplayer.ui.theme.ExoPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VideoPlayerScreen(VideoMockData.sources)
                }
            }
        }
    }
}
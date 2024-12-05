package com.mun.bonecci.exoplayer.ui

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import com.mun.bonecci.exoplayer.data.VideoMockData
import com.mun.bonecci.exoplayer.ui.component.ExoPlayerView

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerScreen(videoUrls: List<String>) {
    var currentVideoIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ExoPlayerView(
            videoUrl = videoUrls[currentVideoIndex],
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onReadyCallback = {
                it.playWhenReady = true
            },
            onEndedCallback = {
                currentVideoIndex++
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (currentVideoIndex > 0) {
                        currentVideoIndex -= 1
                    }
                },
                enabled = currentVideoIndex > 0
            ) {
                Text("Previous")
            }

            Text(
                text = "Now Playing: ${currentVideoIndex + 1}/${videoUrls.size}",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Button(
                onClick = {
                    if (currentVideoIndex < videoUrls.size - 1) {
                        currentVideoIndex += 1
                    }
                },
                enabled = currentVideoIndex < videoUrls.size - 1
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVideoPlayerScreen() {
    VideoPlayerScreen(VideoMockData.sources)
}
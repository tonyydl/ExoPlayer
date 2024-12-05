package com.mun.bonecci.exoplayer.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun ExoPlayerView(
    videoUrl: String,
    modifier: Modifier = Modifier,
    onIdleCallback: (ExoPlayer) -> Unit = {},
    onBufferingCallback: (ExoPlayer) -> Unit = {},
    onReadyCallback: (ExoPlayer) -> Unit = {},
    onEndedCallback: (ExoPlayer) -> Unit = {}
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    val mediaSource = remember(videoUrl) {
        MediaItem.fromUri(videoUrl)
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> onIdleCallback(exoPlayer)
                    Player.STATE_BUFFERING -> onBufferingCallback(exoPlayer)
                    Player.STATE_READY -> onReadyCallback(exoPlayer)
                    Player.STATE_ENDED -> onEndedCallback(exoPlayer)
                }
            }
        }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = modifier
    )
}
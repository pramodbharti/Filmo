package com.pramodbharti.filmo.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(onTimeOut: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF26282F))
    ) {
        val currentOnTimeout by rememberUpdatedState(onTimeOut)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ShimmerLoadingScreen(modifier: Modifier = Modifier) {
    Column() {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(130.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(7) {
                Box(
                    modifier = modifier
                        .size(150.dp, 230.dp)
                        .shimmerEffect()
                )
            }
        }

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(7) {
                Box(
                    modifier = modifier
                        .size(150.dp, 230.dp)
                        .shimmerEffect()
                )
            }
        }

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(7) {
                Box(
                    modifier = modifier
                        .size(150.dp, 230.dp)
                        .shimmerEffect()
                )
            }
        }

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(7) {
                Box(
                    modifier = modifier
                        .size(150.dp, 230.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Composable
fun CarouselShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .shimmerEffect()
    )
}

@Composable
fun PosterShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(150.dp, 230.dp)
            .shimmerEffect()
    )
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(durationMillis = 1000)),
        label = "shimmer effect"
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xCED6D6D6),
                Color(0xCEA09A9A),
                Color(0xCED6D6D6)

            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

@Preview(showSystemUi = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen({})
}
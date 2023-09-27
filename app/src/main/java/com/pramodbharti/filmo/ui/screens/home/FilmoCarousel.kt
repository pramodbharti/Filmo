package com.pramodbharti.filmo.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.ui.components.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmoCarousel(
    itemsCount: Int,
    modifier: Modifier = Modifier,
    indicatorShape: Shape = CircleShape,
    indicatorSize: Dp = 6.dp,
    selectedColor: Color = Color.White,
    unSelectedColor: Color = Color.DarkGray,
    indicatorBackgroundColor: Color = Color.Transparent,
    indicatorBackgroundShape: Shape = CircleShape,
    indicatorPadding: PaddingValues = PaddingValues(bottom = 28.dp),
    indicatorAlignment: Alignment = Alignment.BottomCenter,
    autoSlideDuration: Long = 2000L,
    pagerState: PagerState = rememberPagerState { itemsCount },
    itemContent: @Composable (index: Int, pagerState: PagerState) -> Unit
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(autoSlideDuration)
                    val nextPage = (currentPage + 1).mod(itemsCount)
                    pagerState.animateScrollToPage(nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            itemContent(page, pagerState)
        }

        Surface(
            modifier = Modifier
                .align(indicatorAlignment)
                .padding(indicatorPadding),
            shape = indicatorBackgroundShape,
            color = indicatorBackgroundColor
        ) {
            CarouselIndicators(
                totalDots = itemsCount,
                selectedIndex = if (isDragged)
                    pagerState.currentPage
                else
                    pagerState.targetPage,
                selectedColor = selectedColor,
                unSelectedColor = unSelectedColor,
                shape = indicatorShape,
                dotSize = indicatorSize,
                modifier = Modifier.padding(6.dp)
            )
        }

    }


}

@Composable
fun CarouselIndicators(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    shape: Shape,
    dotSize: Dp,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(totalDots) { dot ->
            CarouselIndicator(
                size = dotSize,
                color = if (dot == selectedIndex) selectedColor else unSelectedColor,
                shape = shape
            )

            if (dot != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun CarouselIndicator(
    size: Dp,
    color: Color,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(color)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun FilmoCarouselPreview() {

    FilmoCarousel(itemsCount = dummyMovies.size) { index, pagerSate ->
        CarouselItem(
            movieItem = dummyMovies[0],
            modifier = Modifier.carouselTransition(index, pagerState = pagerSate)
        )
    }
}

@Preview
@Composable
fun ComposeIndicatorPreview() {
    CarouselIndicator(8.dp, Color.Red, CircleShape)
}

@Preview
@Composable
fun ComposeIndicatorsPreview() {
    CarouselIndicators(5, 3, Color.Red, Color.White, CircleShape, 8.dp)
}
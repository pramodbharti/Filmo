package com.pramodbharti.filmo.ui.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.ui.Constants
import com.pramodbharti.filmo.ui.carouselTransition
import com.pramodbharti.filmo.ui.models.MovieItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme


@Composable
fun CarouselItem(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RectangleShape
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("${Constants.IMAGE_URL_500}${movieItem.poster}")
                    .crossfade(true)
                    .build(),
                contentDescription = movieItem.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = movieItem.backdrop),
                error = painterResource(id = movieItem.backdrop),
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Black.copy(alpha = 0.0f),
                                0.6f to Color.Black.copy(alpha = 0.8f),
                                1f to Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = "John Wick 4",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Text(
                    text = "Crime, Thriller",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(modifier: Modifier = Modifier) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        FilmoCarousel(itemsCount = dummyMovies.size) { index, pagerState ->
            CarouselItem(
                movieItem = dummyMovies[index]
            )
        }
        MoviesSection(title = "Trending") {
            MoviePosterList(movies = dummyMovies)
        }
        MoviesSection(title = "Popular") {
            MoviePosterList(movies = dummyMovies)
        }
        MoviesSection(title = "Trending") {
            MoviePosterList(movies = dummyMovies)
        }
        MoviesSection(title = "Popular") {
            MoviePosterList(movies = dummyMovies)
        }
    }
}

@Composable
fun MoviesSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun MoviePosterList(movies: List<MovieItem>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MoviePoster(movieItem = movie)
        }
    }
}

@Composable
fun MoviePoster(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("${Constants.IMAGE_URL_500}${movieItem.poster}")
                .crossfade(true)
                .build(),
            contentDescription = movieItem.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = movieItem.poster),
            error = painterResource(id = movieItem.poster),
            modifier = Modifier.size(150.dp, 230.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CarouselItemPReview() {
    FilmoTheme {
        CarouselItem(movieItem = dummyMovies[1])
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MoviesScreenPreview() {
    FilmoTheme {
        MoviesScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MovieSectionPreview() {
    FilmoTheme {
        MoviesSection(title = "Trending") {
            MoviePosterList(movies = dummyMovies)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePosterListPreview() {
    FilmoTheme {
        MoviePosterList(
            movies = dummyMovies
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePosterPreview() {
    FilmoTheme {
        MoviePoster(
            movieItem = dummyMovies[0]
        )
    }
}

val dummyMovies: List<MovieItem> = listOf(
    MovieItem(
        123,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    ), MovieItem(
        133,
        "Testing one",
        R.drawable.poster2,
        R.drawable.back_drop2,
        ""
    ), MovieItem(
        1230,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    ),
    MovieItem(
        123,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    ), MovieItem(
        133,
        "Testing one",
        R.drawable.poster2,
        R.drawable.back_drop2,
        ""
    ), MovieItem(
        1230,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    )
)



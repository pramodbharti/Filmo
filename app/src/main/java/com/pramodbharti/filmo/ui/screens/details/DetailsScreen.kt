package com.pramodbharti.filmo.ui.screens.details

import android.service.autofill.UserData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.dummydata.dummyCastData
import com.pramodbharti.filmo.dummydata.dummyGenreList
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.Constants
import com.pramodbharti.filmo.ui.components.CastItemsRow
import com.pramodbharti.filmo.ui.components.CastSlots
import com.pramodbharti.filmo.ui.components.MediaItemsPosterRow
import com.pramodbharti.filmo.ui.components.MediaSlots
import com.pramodbharti.filmo.ui.components.TagItemsRow
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    movieItem: MediaItem? = null,
    movieId: Int? = null,
    viewModel: MovieDetailsViewModel = viewModel(factory = MovieDetailsViewModel.Factory)
) {
    val movieDetailsUiState by viewModel.movieDetailsUiState.collectAsState()
    when (val state = movieDetailsUiState) {
        is MovieDetailUiState.Error -> {}
        MovieDetailUiState.Loading -> {}
        is MovieDetailUiState.Success -> {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                ItemDetails(movieItem = state.movies.movie)
                CastSlots(title = "Cast") {
                    CastItemsRow(casts = state.movies.casts)
                }
                if (state.movies.similarMovies.isNotEmpty()) {
                    MediaSlots(title = "Similar") {
                        MediaItemsPosterRow(movies = state.movies.similarMovies)
                    }
                }
                if (state.movies.recommendedMovies.isNotEmpty()) {
                    MediaSlots(title = "Recommended for you") {
                        MediaItemsPosterRow(movies = state.movies.recommendedMovies)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ItemDetailsScreen() {
    FilmoTheme {
        DetailsScreen(movieItem = dummyMovies[1])
    }
}

@Composable
fun ItemDetails(movieItem: MediaItem, modifier: Modifier = Modifier) {
    Box(modifier) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data("${Constants.IMAGE_URL_500}${movieItem.poster}")
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

        ItemDetailsSection(
            movieItem = movieItem,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ItemDetailsSection(movieItem: MediaItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colorStops = arrayOf(
                    0.0f to Color.Black.copy(alpha = 0.0f),
                    0.1f to Color.Black.copy(alpha = 0.5f),
                    0.2f to Color.Black.copy(alpha = 0.7f),
                    0.7f to Color.Black.copy(alpha = 0.9f),
                    1f to Color.Black.copy(alpha = 1f)
                )
            )
        )
    ) {
        TitleAndFavorite(title = movieItem.title)
        TagItemsRow(tags = dummyGenreList)
        AboutSection(overview = movieItem.overview)
    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier) {
    Row {

    }
}

@Composable
fun TitleAndFavorite(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.LightGray,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_favorite),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(8.dp)
                    .size(20.dp)
            )
        }
    }
}

@Composable
fun AboutSection(overview: String, modifier: Modifier = Modifier) {
    Text(
        text = overview,
        style = MaterialTheme.typography.titleMedium,
        color = Color.LightGray,
        fontSize = 10.sp,
        fontWeight = FontWeight.Light,
        modifier = modifier.padding(start = 16.dp, bottom = 16.dp),
        lineHeight = 18.sp
    )
}

@Preview
@Composable
fun ItemDetailsPreview() {
    ItemDetails(movieItem = dummyMovies[0])
}





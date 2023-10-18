package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.Constants
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme

@Composable
fun CarouselItem(
    movieItem: MediaItem,
    onMediaItemClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(enabled = true) { onMediaItemClick(movieItem) },
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
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
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
                    text = movieItem.title,
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

@Preview(showBackground = true)
@Composable
fun CarouselItemPReview() {
    FilmoTheme {
        CarouselItem(movieItem = dummyMovies[1], onMediaItemClick = {})
    }
}
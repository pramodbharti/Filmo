package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.ui.Constants
import com.pramodbharti.filmo.ui.models.MediaItem

@Composable
fun MediaItemsPosterRow(movies: List<MediaItem>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MediaPoster(movieItem = movie)
        }
    }
}

@Composable
fun MediaPoster(movieItem: MediaItem, modifier: Modifier = Modifier) {
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

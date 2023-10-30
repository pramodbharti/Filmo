package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.Constants
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.filmoShapes
import com.pramodbharti.filmo.ui.toFullImageUrl

@Composable
fun MediaItemsPosterRow(
    medias: List<MediaItem>,
    modifier: Modifier = Modifier,
    onMediaItemClick: (MediaItem) -> Unit = {},
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(medias) { media ->
            MediaPoster(mediaItem = media, onMediaItemClick = onMediaItemClick)
        }
    }
}

@Composable
fun MediaPoster(
    mediaItem: MediaItem,
    onMediaItemClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable(enabled = true) { onMediaItemClick(mediaItem) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(mediaItem.poster.toFullImageUrl())
                .crossfade(true)
                .build(),
            contentDescription = mediaItem.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier.size(150.dp, 230.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PosterPreview() {
    MediaItemsPosterRow(medias = dummyMovies)
}

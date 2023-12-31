package com.pramodbharti.filmo.ui.screens.details

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.dummydata.dummyCastData
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.components.CastItemsRow
import com.pramodbharti.filmo.ui.components.CastSlots
import com.pramodbharti.filmo.ui.components.ErrorScreen
import com.pramodbharti.filmo.ui.components.MediaItemsPosterRow
import com.pramodbharti.filmo.ui.components.MediaSlots
import com.pramodbharti.filmo.ui.components.ShimmerLoadingDetailsScreen
import com.pramodbharti.filmo.ui.components.TagItemsRow
import com.pramodbharti.filmo.ui.formatDate
import com.pramodbharti.filmo.ui.models.MediaDetails
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme
import com.pramodbharti.filmo.ui.toFullImageUrl

@Composable
fun DetailsScreen(
    onMediaClick: (MediaItem) -> Unit,
    onFavClicked: (MediaItem) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: MediaDetailsViewModel = viewModel(factory = MediaDetailsViewModel.Factory)
) {
    val movieDetailsUiState by viewModel.mediaDetailsUiState.collectAsStateWithLifecycle()
    DetailsScreen(
        uiState = movieDetailsUiState,
        onMediaClick = onMediaClick,
        onBackPressed = onBackPressed,
        onFavClicked = onFavClicked
    )
}

@Composable
fun DetailsScreen(
    uiState: MediaDetailUiState,
    modifier: Modifier = Modifier,
    onMediaClick: (MediaItem) -> Unit = {},
    onBackPressed: () -> Unit = {},
    onFavClicked: (MediaItem) -> Unit = {}
) {
    when (uiState) {
        is MediaDetailUiState.Error -> {
            ErrorScreen(msg = uiState.msg)
        }

        is MediaDetailUiState.Loading -> {
            ShimmerLoadingDetailsScreen()
        }

        is MediaDetailUiState.Success -> {
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                ItemDetails(
                    mediaItem = uiState.mediaDetails.media,
                    onFavClicked = onFavClicked,
                    onBackPressed = onBackPressed
                )
                OverviewSection(overview = uiState.mediaDetails.media.overview)
                CastSlots(title = "Cast") {
                    CastItemsRow(casts = uiState.mediaDetails.casts)
                }
                AnimatedVisibility(visible = uiState.mediaDetails.similarMedia.isNotEmpty()) {
                    MediaSlots(title = "Similar") {
                        MediaItemsPosterRow(
                            medias = uiState.mediaDetails.similarMedia,
                            onMediaItemClick = onMediaClick
                        )
                    }
                }
                AnimatedVisibility(visible = uiState.mediaDetails.recommendedMedia.isNotEmpty()) {
                    MediaSlots(title = "Recommended for you") {
                        MediaItemsPosterRow(
                            medias = uiState.mediaDetails.recommendedMedia,
                            onMediaItemClick = onMediaClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemDetails(
    mediaItem: MediaItem,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onFavClicked: (MediaItem) -> Unit
) {
    Box(modifier) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(mediaItem.backdrop.toFullImageUrl())
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
        )

        IconButton(
            onClick = onBackPressed,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .background(color = Color.Gray.copy(alpha = 0.5f), shape = CircleShape)
                    .padding(PaddingValues(8.dp))
                    .size(20.dp),
                tint = Color.White
            )
        }

        ItemDetailsSection(
            mediaItem = mediaItem,
            modifier = Modifier.align(Alignment.BottomStart),
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(
                onClick = { onFavClicked(mediaItem) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(
                            color = Color.Gray.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                        .padding(PaddingValues(8.dp))
                        .size(20.dp)
                )
            }

            Text(
                text = mediaItem.vote,
                modifier = Modifier
                    .background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(PaddingValues(8.dp))
                    .size(20.dp),
                style = TextStyle(color = Color.White),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemDetailsSection(
    mediaItem: MediaItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
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
            .fillMaxWidth()
    ) {
        Text(
            text = mediaItem.title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        mediaItem.genres?.let {
            TagItemsRow(tags = it)
        }
        Text(
            text = mediaItem.releaseDate.formatDate(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 18.dp, top = 0.dp, bottom = 8.dp, end = 8.dp)
        )
    }
}

@Composable
fun OverviewSection(overview: String, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    Column {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Overview",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Overview"
                )
            }
        }
        AnimatedVisibility(visible = isExpanded) {
            Text(
                text = overview,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 12.sp,
                modifier = modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO, name = "Light")
@Composable
fun DetailsScreenPreview() {
    FilmoTheme {
        Surface {
            DetailsScreen(
                uiState = MediaDetailUiState.Success(
                    MediaDetails(
                        dummyMovies[0],
                        dummyCastData,
                        dummyMovies,
                        dummyMovies
                    )
                )
            )
        }
    }
}





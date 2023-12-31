package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.dummydata.dummyCastData
import com.pramodbharti.filmo.ui.Constants
import com.pramodbharti.filmo.ui.models.Cast
import com.pramodbharti.filmo.ui.toFullImageUrl

@Composable
fun CastItemsRow(casts: List<Cast>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(casts) {
            CastItem(cast = it)
        }
    }
}

@Composable
fun CastItem(cast: Cast, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(cast.photo.toFullImageUrl())
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop,
            contentDescription = cast.name,
            modifier = Modifier
                .height(80.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
        )
        Text(
            text = cast.name,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CastItemRowPreview() {
    CastItemsRow(casts = dummyCastData)
}
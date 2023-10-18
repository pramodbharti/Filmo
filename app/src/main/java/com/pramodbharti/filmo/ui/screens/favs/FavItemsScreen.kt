package com.pramodbharti.filmo.ui.screens.favs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.dummydata.dummyMovies
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.theme.FilmoTheme

@Composable
fun FavItemsScreen(
    items: List<MediaItem> = emptyList(),
    modifier: Modifier = Modifier,
    onDeleteClick: (MediaItem) -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items) { item ->
            FavItem(item = item, onDeleteClick = onDeleteClick)
        }
    }
}

@Composable
fun FavItem(item: MediaItem, modifier: Modifier = Modifier, onDeleteClick: (MediaItem) -> Unit) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = item.poster),
                contentDescription = item.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(130.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
            )
            ItemDetails(mediaItem = item, modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete",
                modifier = Modifier.clickable { onDeleteClick(item) }
            )
        }
    }
}

@Composable
fun ItemDetails(mediaItem: MediaItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.Start) {
        Text(
            text = mediaItem.title,
            modifier = Modifier,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(text = "Time: 2H 20M")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(showBackground = false, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FavItemPreview() {
    FilmoTheme {
        FavItemsScreen(items = dummyMovies + dummyMovies, onDeleteClick = {})
    }
}





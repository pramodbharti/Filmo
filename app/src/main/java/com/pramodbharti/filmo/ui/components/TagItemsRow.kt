package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagItemsRow(tags: List<String>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 8.dp
        )
    ) {
        items(tags) {
            TagItem(tagName = it)
        }
    }
}

@Composable
fun TagItem(tagName: String, modifier: Modifier = Modifier) {
    Text(
        text = tagName,
        style = MaterialTheme.typography.bodySmall,
        color = Color.White,
        fontSize = 10.sp,
        modifier = modifier
            .background(Color.DarkGray)
            .padding(horizontal = 4.dp, vertical = 2.dp)
    )
}
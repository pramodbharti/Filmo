package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.ui.models.SlotHeader

@Composable
fun MediaSlots(
    title: String,
    onSeeAllClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        SlotHeader(
            title = title,
            onSeeAllClick = onSeeAllClick
        )
        content()
    }
}

@Composable
fun CastSlots(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


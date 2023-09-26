package com.pramodbharti.filmo.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CastItem(cast: Cast, modifier: Modifier = Modifier) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current),
            contentDescription = null
        )
        Text(text = cast.name)
    }
}
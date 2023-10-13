package com.pramodbharti.filmo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoAppBar(modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Text(text = "Filmo")
    }, navigationIcon = {
        IconButton(onClick = {}) {
            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = null
            )
        }
    })
}

@Preview
@Composable
fun FilmoAppBarPreview() {
    FilmoAppBar()
}
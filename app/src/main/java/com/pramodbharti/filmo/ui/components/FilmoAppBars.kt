package com.pramodbharti.filmo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ), navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun FilmoAppBarPreview() {
}
package com.pramodbharti.filmo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pramodbharti.filmo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoAppBar(
    title: String,
    canNavigateBack: Boolean = true,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        title = {
            if (canNavigateBack)
                Text(text = title, style = MaterialTheme.typography.titleLarge)
            else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "Filmo")
                }
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
fun FilmoAppBarPreview() {
}
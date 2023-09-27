package com.pramodbharti.filmo.ui.screens.details

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.ui.models.MovieItem
import com.pramodbharti.filmo.ui.screens.home.MoviePosterList
import com.pramodbharti.filmo.ui.screens.home.MoviesScreen
import com.pramodbharti.filmo.ui.screens.home.MoviesSection
import com.pramodbharti.filmo.ui.screens.home.dummyMovies
import com.pramodbharti.filmo.ui.theme.FilmoTheme

@Composable
fun ItemDetailsScreen(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ItemDetails(movieItem = movieItem)
        MoviesSection(title = "Cast") {
            CastItemRow(casts = dummyCastData)
        }
        MoviesSection(title = "Similar") {
            MoviePosterList(movies = dummyMovies)
        }
        MoviesSection(title = "Recommended for you") {
            MoviePosterList(movies = dummyMovies)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemDetailsScreen() {
    FilmoTheme {
        ItemDetailsScreen(movieItem = dummyMovies[1])
    }
}

@Composable
fun ItemDetails(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Box(modifier) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(movieItem.backdrop)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = movieItem.backdrop),
            error = painterResource(id = movieItem.backdrop),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

        ItemDetailsSection(
            movieItem = movieItem,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ItemDetailsSection(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(
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
    ) {
        TitleAndFavorite(title = movieItem.title)
        TagItemsRow(tags = dummyGenreList)
        AboutSection()
    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier) {
Row {

}
}

@Composable
fun TitleAndFavorite(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.LightGray,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_favorite),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(8.dp)
                    .size(20.dp)
            )
        }
    }
}

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

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    Text(
        text = "How to scroll view pager (accompanist library) on button click in jetpack compose Android".repeat(
            3
        ),
        style = MaterialTheme.typography.titleMedium,
        color = Color.LightGray,
        fontSize = 10.sp,
        fontWeight = FontWeight.Light,
        modifier = modifier.padding(start = 16.dp, bottom = 16.dp),
        lineHeight = 18.sp
    )
}

@Composable
fun CastItemRow(casts: List<Cast>, modifier: Modifier = Modifier) {
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
                .data(cast.photo)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = cast.photo),
            error = painterResource(id = cast.photo),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .height(80.dp)
        )
        Text(
            text = cast.name,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ItemDetailsPreview() {
    ItemDetails(movieItem = dummyMovies[0])
}

@Preview(showBackground = true)
@Composable
fun TitleAndFavoritePreview() {
    TitleAndFavorite(title = dummyGenreList[0])
}

@Preview(showBackground = true)
@Composable
fun TagItemsRowPreview() {
    TagItemsRow(tags = dummyGenreList)
}

@Preview(showBackground = true)
@Composable
fun AboutSectionPReview() {
    AboutSection()
}

@Preview(showBackground = true)
@Composable
fun CastItemRowPreview() {
    CastItemRow(casts = dummyCastData)
}

@Preview(showBackground = true)
@Composable
fun CastItemPreview() {
    CastItem(cast = dummyCastData[0])
}

val dummyGenreList = listOf(
    "Adventure",
    "Action",
    "Animation",
    "Documentary",
    "Fantasy",
    "History",
    "Science Fiction"
)

val dummyCastData = listOf(
    Cast("Pramod Bharti", R.drawable.dddd),
    Cast("Konark Chakra", R.drawable.placeholder),
    Cast("Unknown Profile", R.drawable.profile_picture),
    Cast("Pramod Bharti", R.drawable.dddd),
    Cast("Konark Chakra", R.drawable.placeholder),
    Cast("Unknown Profile", R.drawable.profile_picture)
)
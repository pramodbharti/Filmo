package com.pramodbharti.filmo.dummydata

import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.screens.details.Cast

val dummyMovies: List<MediaItem> = listOf(
    MediaItem(
        123,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    ), MediaItem(
        133,
        "Testing one",
        R.drawable.poster2,
        R.drawable.back_drop2,
        ""
    ), MediaItem(
        1230,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    ),
    MediaItem(
        123,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    ), MediaItem(
        133,
        "Testing one",
        R.drawable.poster2,
        R.drawable.back_drop2,
        ""
    ), MediaItem(
        1230,
        "Testing one",
        R.drawable.poster1,
        R.drawable.back_drop1,
        ""
    )
)


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
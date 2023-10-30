package com.pramodbharti.filmo.dummydata

import com.pramodbharti.filmo.R
import com.pramodbharti.filmo.ui.models.MediaItem
import com.pramodbharti.filmo.ui.models.Cast

val dummyMovies: List<MediaItem> = listOf(
    MediaItem(
        123,
        "Testing one",
        "",
        "",
        "with a default value of LocalContentColor. If imageVector has no intrinsic size, this component will use the recommended default size. Icon is an opinionated component designed to be used with single-color icons so that t",
        "2023-10-25",
        genres = listOf("Adventure", "Drama"),
        vote = "8.3"
    ), MediaItem(
        133,
        "Testing one",
        "",
        "",
        "with a default value of LocalContentColor. If imageVector has no intrinsic size, this component will use the recommended default size. Icon is an opinionated component designed to be used with single-color icons so that t",
        "2023-10-25",
        genres = listOf("Adventure", "Drama"),
        vote = "8.3"
    ), MediaItem(
        1230,
        "Testing one",
        "",
        "",
        "with a default value of LocalContentColor. If imageVector has no intrinsic size, this component will use the recommended default size. Icon is an opinionated component designed to be used with single-color icons so that t",
        "",
        vote = "8.3"
    ),
    MediaItem(
        123,
        "Testing one",
        "",
        "",
        "",
        "",
        vote = "8.3"
    ), MediaItem(
        133,
        "Testing one",
        "",
        "",
        "",
        "",
        vote = "8.3"
    ), MediaItem(
        1230,
        "Testing one",
        "",
        "",
        "",
        "",
        vote = "8.3"
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
    Cast("Pramod Bharti", ""),
    Cast("Konark Chakra", ""),
    Cast("Unknown Profile", ""),
    Cast("Pramod Bharti", ""),
    Cast("Konark Chakra", ""),
    Cast("Unknown Profile", "")
)
package com.pramodbharti.filmo.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.icu.util.LocaleData
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.formatDate(): String {
    val fullDatePattern = "yyyy-MM-dd"
    val monthYearPattern = "MMM dd, yyyy"
    val localDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val fullDateFormatter = DateTimeFormatter.ofPattern(fullDatePattern)
        val monthYearFormatter = DateTimeFormatter.ofPattern(monthYearPattern)
        LocalDate
            .parse(this, fullDateFormatter)
            .format(monthYearFormatter)
    } else {
        val fullDateFormatter = SimpleDateFormat(fullDatePattern, Locale.getDefault())
        val monthYearFormatter = SimpleDateFormat(monthYearPattern, Locale.getDefault())
        fullDateFormatter.parse(this)?.let { monthYearFormatter.format(it) }
    }
    return localDate.toString()
}

fun String.toFullImageUrl(): String = Constants.IMAGE_URL_500 + this

fun Context.openYoutubeLink(url: String) {
    val intentApp = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("vnd.youtube:$url")
    )
    val intentBrowser = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://www.youtube.com/watch?v=$url")
    )
    try {
        this.startActivity(intentApp)
    } catch (ex: ActivityNotFoundException) {
        this.startActivity(intentBrowser)
    }
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}

fun String.toYoutubeThumbUrl(): String = Constants.YOUTUBE_THUMB_URL + this + "/0.jpg"

fun Double.roundTo(decimalPlaces: Int): String = "%.${decimalPlaces}f".format(this)
package com.husqvarna.dsassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class Utils(private val context: Context) {

    fun isOnline(): Boolean {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}

fun getReleaseDate(value: String): String {
    return if(value.isEmpty()) ""
    else {
        val date = DateTime.parse(value)
        val format = DateTimeFormat.forPattern("dd MMM yyyy")
        format.print(date)
    }
}

fun getMovieRuntime(runTime: Int): String {
    val builder = StringBuilder()
    if (runTime > 0) {
        val hours = runTime / 60
        val minutes = runTime % 60
        if (hours > 0) {
            builder.append(hours)
            builder.append("h")
        }
        if (minutes > 0) {
            if (builder.isNotEmpty()) {
                builder.append(" ")
            }
            builder.append(minutes)
            builder.append("mins")
        }
    }
    return builder.toString()
}
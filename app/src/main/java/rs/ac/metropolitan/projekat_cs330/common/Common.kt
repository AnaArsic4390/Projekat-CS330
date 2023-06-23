package rs.ac.metropolitan.projekat_cs330.common


import android.os.Build
import androidx.annotation.RequiresApi
import java.net.URL
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class Common {

    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        private var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        @RequiresApi(Build.VERSION_CODES.O)
        fun dateToString(date: Date): String {
            val myDate: LocalDate =Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            return formatter.format(myDate)
        }

        fun generateAvatarImage(name: String): URL {
            val digest = java.security.MessageDigest.getInstance("MD5")
                .digest(name.toByteArray())
                .joinToString(separator = "") {
                    String.format("%02x", it)
                }
            val url = "https://www.gravatar.com/avatar/${digest}?d=robohash&s=200"
            return URL(url)
        }
    }
}


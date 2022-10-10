package com.jordan.jetpacknavigation.util

import java.lang.Exception
import java.net.URL
import java.util.*

class HttpURLUtil {
    companion object {
        private const val GOOGLE_PLAY_OLD_PREFIX = "market://details?id="
        private const val GOOGLE_PLAY_HOST = "play.google.com"

        private fun isAppInstallation(urlString: String): Boolean {
            if (urlString.startsWith(GOOGLE_PLAY_OLD_PREFIX)) {
                return true
            }

            return try {
                URL(urlString).host.equals(other = GOOGLE_PLAY_HOST, ignoreCase = true)
            } catch (e: Exception) {
                false
            }
        }

        private fun isHttpLink(url: String): Boolean {
            val lowerString = url.lowercase(Locale.ROOT)
            return lowerString.startsWith("http://") || lowerString.startsWith("https://")
        }

        private fun isSafelyURL(urlString: String): Boolean {
            return try {
                isAppInstallation(urlString)
            } catch (e: Exception) {
                false
            }
        }

        fun tryOpenHttpURL(
            urlString: String,
            onURLChecked: (isSafelyURL: Boolean) -> Unit
        ) {
            if (urlString.isNotBlank()) {
                onURLChecked.invoke(isSafelyURL(urlString))
            }
        }
    }
}
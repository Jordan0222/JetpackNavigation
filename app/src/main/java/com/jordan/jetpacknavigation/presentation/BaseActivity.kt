package com.jordan.jetpacknavigation.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.jordan.jetpacknavigation.util.HttpURLUtil

class BaseActivity: AppCompatActivity() {

    open fun tryOpenHttpURL(
        urlString: String,
        onSafelyURL: (() -> Unit)? = null,
        onResult: ((isSuccess: Boolean) -> Unit)?= null
    ) {
        HttpURLUtil.tryOpenHttpURL(
            urlString = urlString,
            onURLChecked = { isSafelyURL ->
                if (isSafelyURL) {
                    onSafelyURL?.invoke() ?: kotlin.run {
                        openExternalWebBrowser(urlString).also { isSuccess ->
                            onResult?.invoke(isSuccess)
                        }
                    }
                } else {
                    openExternalWebBrowser(urlString).also { isSuccess ->
                        onResult?.invoke(isSuccess)
                    }
                }
            }
        )
    }

    private fun openExternalWebBrowser(urlString: String): Boolean {
        return try {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(urlString)
            })

            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }
}
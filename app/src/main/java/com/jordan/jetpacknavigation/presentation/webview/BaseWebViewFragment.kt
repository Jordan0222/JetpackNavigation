package com.jordan.jetpacknavigation.presentation.webview

import android.os.Bundle
import android.os.Parcelable
import android.webkit.WebView
import androidx.navigation.NavOptions
import com.jordan.jetpacknavigation.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimatorArguments(
    val enterAnimatorId: Int? = null,
    val exitAnimatorId: Int? = null,
    val popEnterAnimatorId: Int? = null,
    val popExitAnimatorId: Int? = null
): Parcelable

@Parcelize
data class WebViewArguments(
    val instanceName: String = INSTANCE_NAME,
    val url: String,
    val contentType: String? = CONTENT_TYPE,
    val autoGetTitleIfLoadFinished: Boolean? = false,
    val showSkipIfLoadFail: Boolean? = false,
    val actionId: Int,
    val postData: Map<String, String>? = null,
    val animator: AnimatorArguments? = AnimatorArguments()
): Parcelable {
    companion object {
        const val INSTANCE_NAME = "webview"
        const val WEB_VIEW_BUNDLE_KEY = "WEB_VIEW_BUNDLE_KEY"
        const val CONTENT_TYPE = "application/x-www-form-urlencoded"
    }

    fun toBundle(): Bundle = Bundle().also {
        it.putParcelable(WEB_VIEW_BUNDLE_KEY, this)
    }
}

class WebViewFragmentHelper {
    companion object {
        fun <T: BaseFragment> launch(fragment: T, argument: WebViewArguments) {
            fragment.tryOpenHttpURL(
                urlString = argument.url,
                onSafelyURL = {
                    fragment.redirect(
                        actionId = argument.actionId,
                        bundle = argument.toBundle(),
                        navOptions = argument.animator?.let { animator ->
                            if (animator.enterAnimatorId != null || animator.exitAnimatorId != null ||
                                animator.popEnterAnimatorId != null || animator.popExitAnimatorId != null
                            ) {
                                NavOptions.Builder().also {
                                    animator.enterAnimatorId?.run {
                                        it.setEnterAnim(this)
                                    }

                                    animator.exitAnimatorId?.run {
                                        it.setExitAnim(this)
                                    }

                                    animator.popEnterAnimatorId?.run {
                                        it.setPopEnterAnim(this)
                                    }

                                    animator.popExitAnimatorId?.run {
                                        it.setPopExitAnim(this)
                                    }
                                }.build()
                            } else {
                                null
                            }
                        }
                    )
                }
            )
        }
    }
}

@AndroidEntryPoint
abstract class BaseWebViewFragment: BaseFragment() {

    private var webView: WebView? = null


}
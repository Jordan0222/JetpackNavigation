package com.jordan.jetpacknavigation.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.jordan.jetpacknavigation.presentation.extension.SafeNavigation
import com.jordan.jetpacknavigation.presentation.extension.findActivity

open class BaseFragment : Fragment() {
    fun redirect(actionId: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
        SafeNavigation.findNavController(view)?.navigate(actionId, bundle, navOptions)
    }

    fun tryOpenHttpURL(
        urlString: String,
        onSafelyURL: (() -> Unit)? = null,
        onResult: ((isSuccess: Boolean) -> Unit)?= null
    ) {
        findHostActivity()?.tryOpenHttpURL(
            urlString = urlString,
            onSafelyURL = onSafelyURL,
            onResult = { isSuccess ->
                onResult?.invoke(isSuccess)
            }
        )
    }
}

fun BaseFragment.findHostActivity() = (findActivity() as? BaseActivity)
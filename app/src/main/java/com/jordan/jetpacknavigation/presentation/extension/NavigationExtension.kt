package com.jordan.jetpacknavigation.presentation.extension

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation

class SafeNavigation {
    companion object {
        fun findNavController(view: View?): NavController? {
            if (view == null) return null
            return try {
                Navigation.findNavController(view)
            } catch (e: IllegalStateException) {
                null
            }
        }
    }
}
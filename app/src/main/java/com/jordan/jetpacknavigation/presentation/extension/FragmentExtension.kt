package com.jordan.jetpacknavigation.presentation.extension

import android.app.Activity
import androidx.fragment.app.Fragment

fun Fragment.findActivity(): Activity? {
    return this.activity
}
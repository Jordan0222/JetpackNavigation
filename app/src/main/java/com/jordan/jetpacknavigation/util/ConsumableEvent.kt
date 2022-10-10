package com.jordan.jetpacknavigation.util

open class ConsumableEvent<out  T>(private val content: T) {

    var consumed = false
        private set

    fun getContentIfNotConsumed(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }

    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }
}
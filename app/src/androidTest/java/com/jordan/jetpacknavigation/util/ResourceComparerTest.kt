package com.jordan.jetpacknavigation.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import com.jordan.jetpacknavigation.R

// add jcenter() in settings.gradle
class ResourceComparerTest {
    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setUp() {
        resourceComparer = ResourceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.affirmation1, "I am strong.")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.affirmation1, "I am Jordan.")
        assertThat(result).isFalse()
    }
}
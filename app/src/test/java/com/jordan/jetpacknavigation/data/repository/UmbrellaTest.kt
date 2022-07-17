package com.jordan.jetpacknavigation.data.repository

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UmbrellaTest {

    @Test
    fun totalPrice_sunnyDay() {
        val umbrella = Umbrella()
        val weather = StubWeather()
        weather.fakeIsSunny = true

        val actual = umbrella(weather, 3, 100)
        val expected = 270

        assertThat(actual).isEqualTo(expected)
    }
}
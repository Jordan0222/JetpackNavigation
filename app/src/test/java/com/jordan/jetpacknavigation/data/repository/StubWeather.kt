package com.jordan.jetpacknavigation.data.repository

import com.jordan.jetpacknavigation.domain.repository.IWeather

class StubWeather: IWeather {

    var fakeIsSunny = false

    override fun isSunny(): Boolean {
        return fakeIsSunny
    }
}
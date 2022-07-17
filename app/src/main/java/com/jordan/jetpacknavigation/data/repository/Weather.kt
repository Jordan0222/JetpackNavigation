package com.jordan.jetpacknavigation.data.repository

import com.jordan.jetpacknavigation.domain.repository.IWeather

class Weather: IWeather {
    override fun isSunny(): Boolean {
        return true
    }
}
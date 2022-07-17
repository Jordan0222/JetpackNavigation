package com.jordan.jetpacknavigation.data.repository

import com.jordan.jetpacknavigation.domain.repository.IWeather

class Umbrella {

    operator fun invoke(weather: IWeather, quantity: Int, price: Int): Int {
        val isSunny = weather.isSunny()
        var price = quantity * price

        if (isSunny) {
            price = (price * 0.9).toInt()
        }

        return price
    }
}
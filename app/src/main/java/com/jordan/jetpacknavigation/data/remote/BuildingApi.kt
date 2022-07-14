package com.jordan.jetpacknavigation.data.remote

import retrofit2.http.GET

interface BuildingApi {

    @GET("/EAS/Apps/systex/hr_elearning/hr_elearning_20220602_181350.json")
    suspend fun getBuildingItems(): Building
}
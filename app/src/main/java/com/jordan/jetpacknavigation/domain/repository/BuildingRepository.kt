package com.jordan.jetpacknavigation.domain.repository

import com.jordan.jetpacknavigation.data.remote.BuildingItem

interface BuildingRepository {

    suspend fun getBuildItems(page: Int, pageSize: Int): Result<List<BuildingItem>>
}
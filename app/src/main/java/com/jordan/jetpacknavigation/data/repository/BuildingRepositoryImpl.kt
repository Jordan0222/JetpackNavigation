package com.jordan.jetpacknavigation.data.repository

import com.jordan.jetpacknavigation.data.remote.Building
import com.jordan.jetpacknavigation.data.remote.BuildingApi
import com.jordan.jetpacknavigation.data.remote.BuildingItem
import com.jordan.jetpacknavigation.domain.repository.BuildingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BuildingRepositoryImpl(
    private val buildingApi: BuildingApi
): BuildingRepository {
    override suspend fun getBuildItems(page: Int, pageSize: Int): Result<List<BuildingItem>> {
        return try {
            var response: Building
            withContext(Dispatchers.IO) {
                response = buildingApi.getBuildingItems()
            }
            val buildingItems = response.DataList
            val startingIndex = page * pageSize
            if (startingIndex + pageSize <= buildingItems.size) {
                Result.success(
                    buildingItems.slice(startingIndex until startingIndex + pageSize)
                )
            } else Result.success(emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
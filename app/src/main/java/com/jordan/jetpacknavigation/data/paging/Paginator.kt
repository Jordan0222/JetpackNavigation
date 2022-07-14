package com.jordan.jetpacknavigation.data.paging

interface Paginator<Key, Item> {
    suspend fun loadNextItem()
    fun reset()
}
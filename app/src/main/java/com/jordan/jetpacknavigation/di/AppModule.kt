package com.jordan.jetpacknavigation.di

import android.content.Context
import com.jordan.jetpacknavigation.adapter.ItemAdapter
import com.jordan.jetpacknavigation.adapter.WordAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideItemAdapter(
        @ApplicationContext context: Context
    ) = ItemAdapter(context)

    @Singleton
    @Provides
    fun provideWordAdapter(
        @ApplicationContext context: Context
    ) = WordAdapter(context)
}
package com.jordan.jetpacknavigation.di

import android.content.Context
import com.jordan.jetpacknavigation.adapter.ItemAdapter
import com.jordan.jetpacknavigation.adapter.WordAdapter
import com.jordan.jetpacknavigation.data.remote.BuildingApi
import com.jordan.jetpacknavigation.data.repository.BuildingRepositoryImpl
import com.jordan.jetpacknavigation.domain.repository.BuildingRepository
import com.jordan.jetpacknavigation.util.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    @Provides
    @Singleton
    fun provideBuildingApi(): BuildingApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideBuildingRepository(
        api: BuildingApi,
    ): BuildingRepository {
        return BuildingRepositoryImpl(api)
    }
}
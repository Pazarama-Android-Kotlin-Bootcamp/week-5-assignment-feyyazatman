package com.merttoptas.retrofittutorial.data.di

import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by merttoptas on 17.10.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    //Retrofit
    //Gson
    //OkHttp
    //Interceptor
    //ApiService

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideBaseUrl(): String {
        return "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}
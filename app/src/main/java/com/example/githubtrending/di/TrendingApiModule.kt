package com.example.githubtrending.di

import com.example.githubtrending.api.TrendingApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://run.mocky.io/"
@Module
class TrendingApiModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideTrendingService(retrofit: Retrofit) : TrendingApiService {
        return retrofit.create(TrendingApiService::class.java)
    }
}
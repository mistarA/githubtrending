package com.example.githubtrending.di

import com.example.githubtrending.viewmodel.TrendingViewModel
import dagger.Component

@Component(modules = [AppModule::class, DatabaseModule::class, TrendingApiModule::class])
interface ApplicationComponent {

    fun inject(trendingViewModel: TrendingViewModel)
}
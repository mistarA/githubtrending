package com.example.githubtrending.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.githubtrending.api.TrendingApiService
import com.example.githubtrending.di.AppModule
import com.example.githubtrending.di.DaggerApplicationComponent
import com.example.githubtrending.di.DatabaseModule
import com.example.githubtrending.di.TrendingApiModule
import com.example.githubtrending.store.AuthorDao
import com.example.githubtrending.store.AuthorMemoryStore
import com.example.githubtrending.store.SharedPreferencesHelper
import javax.inject.Inject

class TrendingViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var authorDao: AuthorDao

    @Inject
    lateinit var githubService: TrendingApiService

    @Inject
    lateinit var trendingAuthor: AuthorMemoryStore

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper


    init {
        DaggerApplicationComponent.builder()
            .appModule(AppModule())
            .databaseModule(DatabaseModule(application))
            .trendingApiModule(TrendingApiModule())
            .build()
            .inject(this)
    }

}
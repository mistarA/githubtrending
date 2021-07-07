package com.example.githubtrending.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.githubtrending.store.AuthorMemoryStore
import com.example.githubtrending.store.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideTrendingAuthor() : AuthorMemoryStore {
        return AuthorMemoryStore()
    }

    @Provides
    fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences) : SharedPreferencesHelper {
        return SharedPreferencesHelper(sharedPreferences)
    }

    @Provides
    fun provideDefaultSharedPreference(application: Application) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
    }
}
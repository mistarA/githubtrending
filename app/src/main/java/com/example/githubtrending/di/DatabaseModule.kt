package com.example.githubtrending.di

import android.app.Application
import com.example.githubtrending.store.AppDatabase
import com.example.githubtrending.store.AuthorDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun providesDatabase(): AppDatabase {
        return AppDatabase.getInstance(application.applicationContext)
    }

    @Provides
    fun providesAuthorDao(appDatabase: AppDatabase): AuthorDao {
        return appDatabase.authorDao()
    }
}
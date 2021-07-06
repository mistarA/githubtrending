package com.example.githubtrending.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.githubtrending.cache.TrendingAuthor
import com.example.githubtrending.database.AppDatabase
import com.example.githubtrending.database.AuthorDao
import com.example.githubtrending.repository.AuthorRepository
import com.example.githubtrending.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication() : Application {
        return application
    }

    @Provides
    fun providesDatabase() : AppDatabase {
        return AppDatabase.getInstance(application.applicationContext)
    }

    @Provides
    fun provideRepository() : AuthorRepository {
        return AuthorRepository()
    }

    @Provides
    fun provideTrendingAuthor() : TrendingAuthor {
        return TrendingAuthor()
    }

    @Provides
    fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences) : SharedPreferencesHelper {
        return SharedPreferencesHelper(sharedPreferences)
    }

    @Provides
    fun provideDefaultSharedPreference(application: Application) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
    }

    @Provides
    fun providesAuthorDao(appDatabase: AppDatabase) : AuthorDao {
        return appDatabase.authorDao()
    }
}
package com.example.githubtrending.database

import android.content.Context
import androidx.room.*
import com.example.githubtrending.model.Author

const val DATABASE_NAME = "app_db"
@Database(entities = [Author::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authorDao() : AuthorDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context : Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) : AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }

}
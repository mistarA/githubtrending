package com.example.githubtrending.store

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubtrending.model.Author
import io.reactivex.Single

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthors(authorList: List<Author>)

    @Query("Select * from author ORDER BY name")
    fun getAuthors() : Single<List<Author>>
}
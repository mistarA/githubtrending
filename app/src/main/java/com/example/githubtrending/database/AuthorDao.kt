package com.example.githubtrending.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubtrending.model.Author
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthors(authorList: List<Author>)

    @Query("Select * from author ORDER BY name")
    fun getAuthors() : Maybe<List<Author>>
}
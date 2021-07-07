package com.example.githubtrending.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class Author(

    @PrimaryKey(autoGenerate = true)
    val authorId: Int,
    val author: String,
    val name: String,
    val avatar: String,
    val url: String,
    val description: String,
    val language: String,
)

package com.example.githubtrending.store

import com.example.githubtrending.model.Author

class AuthorMemoryStore {

    var authorList: List<Author>? = null

    fun get(): List<Author>? {
        return authorList
    }

    fun set(list: List<Author>) {
        authorList = list
    }
}
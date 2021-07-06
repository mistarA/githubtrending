package com.example.githubtrending.cache

import com.example.githubtrending.model.Author

class TrendingAuthor {

     var authorList: List<Author>?
        get() = authorList
        set(value) {
            authorList = value
        }
}
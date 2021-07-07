package com.example.githubtrending.api

import com.example.githubtrending.model.Author
import io.reactivex.Single
import retrofit2.http.GET

interface TrendingApiService {

    @GET("v3/7ef86b70-f1a8-40ab-854c-5d679cd51cd4")
    fun getAuthors(): Single<List<Author>>
}
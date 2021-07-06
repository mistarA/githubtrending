package com.example.githubtrending.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.githubtrending.api.GithubService
import com.example.githubtrending.cache.TrendingAuthor
import com.example.githubtrending.database.AuthorDao
import com.example.githubtrending.di.ApiModule
import com.example.githubtrending.di.AppModule
import com.example.githubtrending.di.DaggerAppComponent
import com.example.githubtrending.model.Author
import com.example.githubtrending.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitHubTrendingViewModel(application: Application) :
    AndroidViewModel(application) {


    var authorList: List<Author>? = null

    @Inject
    lateinit var authorDao: AuthorDao

    @Inject
    lateinit var githubService: GithubService

    @Inject
    lateinit var trendingAuthor: TrendingAuthor

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    init {
        DaggerAppComponent.builder().appModule(AppModule(getApplication())).apiModule(ApiModule())
            .build().inject(this)
        val value = sharedPreferencesHelper.getString("hello")
        Log.d("==== value", value!!)
        makeApiCall()
    }

    private fun makeApiCall() {

        githubService.getTrendingAuthor()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<List<Author>>() {
                override fun onSuccess(list: List<Author>) {
                    Log.d("==== data", list.toString())
                    sharedPreferencesHelper.putString("hello", list[0].description)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }
}
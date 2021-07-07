package com.example.githubtrending.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.githubtrending.api.TrendingApiService
import com.example.githubtrending.di.AppModule
import com.example.githubtrending.di.DaggerApplicationComponent
import com.example.githubtrending.di.DatabaseModule
import com.example.githubtrending.di.TrendingApiModule
import com.example.githubtrending.model.Author
import com.example.githubtrending.store.AuthorDao
import com.example.githubtrending.store.AuthorMemoryStore
import com.example.githubtrending.store.SharedPreferencesHelper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import javax.inject.Inject

private const val LOCAL_DATA_EXPIRY_MINUTES = 120

class TrendingViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var authorDao: AuthorDao

    @Inject
    lateinit var trendingApiService: TrendingApiService

    @Inject
    lateinit var trendingAuthor: AuthorMemoryStore

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    private val disposables = CompositeDisposable()

    val authors by lazy { MutableLiveData<List<Author>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    init {
        DaggerApplicationComponent.builder()
            .appModule(AppModule())
            .databaseModule(DatabaseModule(application))
            .trendingApiModule(TrendingApiModule())
            .build()
            .inject(this)
    }

    fun refresh() {
        Log.d("====", "I was here")
        loading.value = true
        if (!localDataExpired()) {
            if (trendingAuthor.authorList != null) {
                    Log.d("====", "local data")
                    authors.value = trendingAuthor.authorList
                    loading.value = false
            } else {
                fetchAuthorsFromDb()
            }
        } else {
            fetchAuthorsFromNetwork()
        }
    }

    private fun fetchAuthorsFromDb() {
        disposables.add(
            authorDao.getAuthors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Author>>() {
                    override fun onSuccess(list: List<Author>) {
                        Log.d("====", "DataBase data")
                        authors.value = list
                        loading.value = false
                        trendingAuthor.authorList = list
                        dispose()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                        dispose()
                    }

                })
        )
    }

    private fun fetchAuthorsFromNetwork() {
        disposables.add(
            trendingApiService.getAuthors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Author>>() {
                    override fun onSuccess(list: List<Author>) {
                        authors.value = list
                        loading.value = false
                        trendingAuthor.authorList = list
                        saveDataToDb(list)
                        saveDataFetchTime()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    private fun saveDataToDb(list: List<Author>) {
        val dataSingleTask = Single.fromCallable {
            authorDao.insertAuthors(list)
            return@fromCallable true
        }
        disposables.add(
            dataSingleTask.subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<Boolean>(){
                    override fun onSuccess(t: Boolean) {
                        Log.d("====", "Data Saved successfully")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("====", "Error Saving Data")
                    }

                })
        )
    }

    private fun localDataExpired(): Boolean {
        val last = sharedPreferencesHelper.getLong("LAST_DATA_FETCH_TIME")
        if (last  == 0L) return true
        val now = System.currentTimeMillis()

        // 600000 => 600
        val diffMinutes: Long = ((now - last) / (60 * 1000)) % 60
        Log.d("==== time", now.toString())
        Log.d("==== time", last.toString())
        Log.d("==== time", diffMinutes.toString())

        return diffMinutes > LOCAL_DATA_EXPIRY_MINUTES
    }

    fun saveDataFetchTime() {
        val now = System.currentTimeMillis()
        sharedPreferencesHelper.putLong("LAST_DATA_FETCH_TIME", now)
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.clear()
    }

}
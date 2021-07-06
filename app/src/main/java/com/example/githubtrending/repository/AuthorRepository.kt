package com.example.githubtrending.repository

import com.example.githubtrending.api.GithubService
import com.example.githubtrending.cache.TrendingAuthor
import com.example.githubtrending.database.AuthorDao
import com.example.githubtrending.model.Author
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import javax.inject.Inject

class AuthorRepository {

}
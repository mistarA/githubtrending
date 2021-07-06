package com.example.githubtrending.di

import com.example.githubtrending.viewmodel.GitHubTrendingViewModel
import dagger.Component


@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(gitHubTrendingViewModel: GitHubTrendingViewModel)
}
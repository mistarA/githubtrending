package com.example.githubtrending.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtrending.R
import com.example.githubtrending.viewmodel.GitHubTrendingViewModel

class GitHubTrendingActivity : AppCompatActivity() {

    lateinit var gitHubTrendingViewModel: GitHubTrendingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gitHubTrendingViewModel = ViewModelProviders.of(this).get(GitHubTrendingViewModel::class.java)
    }
}
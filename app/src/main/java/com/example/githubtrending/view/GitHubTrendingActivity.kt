package com.example.githubtrending.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.example.githubtrending.databinding.ActivityMainBinding
import com.example.githubtrending.viewmodel.GitHubTrendingViewModel


class GitHubTrendingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var gitHubTrendingViewModel: GitHubTrendingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gitHubTrendingViewModel = ViewModelProviders.of(this).get(GitHubTrendingViewModel::class.java)

        setSupportActionBar(binding.toolbar)
    }
}
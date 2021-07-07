package com.example.githubtrending.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.githubtrending.databinding.ActivityMainBinding
import com.example.githubtrending.viewmodel.TrendingViewModel


class TrendingActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding
    lateinit var trendingViewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        trendingViewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        setContentView(activityMainBinding.root)
    }
}
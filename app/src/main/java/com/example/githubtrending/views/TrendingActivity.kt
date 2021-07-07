package com.example.githubtrending.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubtrending.databinding.ActivityTrendingBinding
import com.example.githubtrending.model.Author
import com.example.githubtrending.viewmodel.TrendingViewModel
import com.example.practice.view.TrendingListAdapter


class TrendingActivity : AppCompatActivity() {

    lateinit var activityTrendingBinding: ActivityTrendingBinding
    lateinit var trendingViewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTrendingBinding = ActivityTrendingBinding.inflate(layoutInflater)
        trendingViewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        setContentView(activityTrendingBinding.root)
        setSupportActionBar(activityTrendingBinding.toolbar)

        trendingViewModel.authors.observe(this, authorListDataObserver)
        trendingViewModel.loading.observe(this, loadingObserver)
        trendingViewModel.loadError.observe(this, errorObserver)

        activityTrendingBinding.authorlistview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        activityTrendingBinding.authorlistview.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )

        trendingViewModel.refresh()
        activityTrendingBinding.reload.setOnClickListener {
            refreshPage()
        }
        activityTrendingBinding.retrybutton.setOnClickListener {
            refreshPage()
        }
    }

    private fun refreshPage() {
        activityTrendingBinding.authorlistview.visibility = View.GONE
        activityTrendingBinding.listError.visibility = View.GONE
        activityTrendingBinding.loadingView.visibility = View.VISIBLE
        trendingViewModel.refresh()
    }

    private val listAdapter = TrendingListAdapter(arrayListOf())
    private val authorListDataObserver = Observer<List<Author>> { list ->
        list?.let {
            activityTrendingBinding.authorlistview.visibility = View.VISIBLE
            listAdapter.updateAuthorList(list)
            activityTrendingBinding.reload.visibility = View.VISIBLE
        }
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        activityTrendingBinding.loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            activityTrendingBinding.authorlistview.visibility = View.VISIBLE
            activityTrendingBinding.listError.visibility = View.GONE
            activityTrendingBinding.reload.visibility = View.GONE
        }
    }

    private val errorObserver = Observer<Boolean> { isError ->
        activityTrendingBinding.listError.visibility = if (isError) View.VISIBLE else View.GONE
        activityTrendingBinding.reload.visibility = View.GONE
    }

}
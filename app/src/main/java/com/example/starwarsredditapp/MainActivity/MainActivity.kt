package com.example.starwarsredditapp.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsredditapp.MainActivity.adapters.ImagesLoadStateAdapter
import com.example.starwarsredditapp.MainActivity.adapters.RedditListAdapter
import com.example.starwarsredditapp.R
import com.example.starwarsredditapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val redditViewModel: RedditViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.StarWarsRedditApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RedditListAdapter()

        //SET ADAPTER AND HEADER / FOOTER ITEMS FOR PAGING
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ImagesLoadStateAdapter { adapter.retry() },
            footer = ImagesLoadStateAdapter { adapter.retry() }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenCreated {
            redditViewModel.redditList.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
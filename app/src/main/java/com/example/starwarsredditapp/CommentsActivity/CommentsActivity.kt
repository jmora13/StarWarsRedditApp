package com.example.starwarsredditapp.CommentsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.starwarsredditapp.CommentsActivity.adapters.CommentsListAdapter
import com.example.starwarsredditapp.R
import com.example.starwarsredditapp.databinding.ActivityCommentsBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException
@AndroidEntryPoint
class CommentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentsBinding
    private val commentsViewModel: CommentsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.StarWarsRedditApp)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CommentsListAdapter()
        binding.commentsRecyclerView.adapter = adapter
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)

        val intent = intent
        val id = intent.getStringExtra("id")
        commentsViewModel.getComments(id)
        //OBSERVABLE DATA FOR COMMENTS, SUBMIT TO RECYCLERVIEW
        commentsViewModel.commentsList.observe(this) { commentsList ->
            commentsList.let { adapter.submitList(it) }
            binding.spinner.visibility = View.GONE
        }

        //GET ID OF SUBREDDIT ARTICLE
        commentsViewModel.commentsItem.observe(this){ commentsItem ->
            //BIND THE IMAGE ASSOCIATED WITH THE REDDIT POST
            if(commentsItem[0].data?.preview != null) {
                bindMediaView(
                    commentsItem[0].data?.preview?.images?.get(0)?.source?.url!!,
                    binding.mediaView
                )
                //IF PREVIEW DOESNT EXIST BUT THUMBNAIL DOES, BIND THUMBNAIL
            } else if(commentsItem[0].data?.thumbnail != null) {
                bindMediaView(
                    commentsItem[0].data?.thumbnail!!,
                    binding.mediaView
                )
            }

        }
    }

    private fun bindMediaView(url: String, mediaView: ImageView){
        val encoded = url.replace("amp;","", false)
        mediaView.apply {
            Glide.with(this)
                .load(encoded )
                .into(mediaView)
        }
    }
}




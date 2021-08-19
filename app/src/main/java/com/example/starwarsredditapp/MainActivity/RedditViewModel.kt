package com.example.starwarsredditapp.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children
import com.example.starwarsredditapp.MainActivity.models.RedditModel.RedditModel
import com.example.starwarsredditapp.MainActivity.data.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class RedditViewModel @Inject constructor(private val repository: RedditRepository): ViewModel() {

    //LIVE DATA REDDIT LISTING
    val redditList: Flow<PagingData<Children>> = repository.getRedditListing().cachedIn(viewModelScope)

}
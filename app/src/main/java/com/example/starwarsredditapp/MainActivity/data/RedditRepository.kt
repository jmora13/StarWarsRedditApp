package com.example.starwarsredditapp.MainActivity.data

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsredditapp.RedditDatabase
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children
import com.example.starwarsredditapp.MainActivity.models.RedditModel.RedditModel
import com.example.starwarsredditapp.MainActivity.api.RedditService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditRepository @Inject constructor(private val redditService: RedditService,
                                           private val remoteKeysDao: RemoteKeysDao,
                                           private val database: RedditDatabase,
                                           private val context: Context
) {


    @OptIn(ExperimentalPagingApi::class)
    fun getRedditListing(): Flow<PagingData<Children>> {
        val pagingSourceFactory = { database.redditDao().getListing() }
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RedditRemoteMediator(
                redditService,
                database,
                remoteKeysDao
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}

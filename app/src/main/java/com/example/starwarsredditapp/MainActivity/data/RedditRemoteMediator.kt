package com.example.starwarsredditapp.MainActivity.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsredditapp.MainActivity.api.RedditService
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children
import com.example.starwarsredditapp.MainActivity.models.RemoteKeys
import com.example.starwarsredditapp.RedditDatabase
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject


private const val INITIAL_INDEX = 1
private const val INITIAL_PAGE_LIMIT = 8


@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator @Inject constructor(
    private val service: RedditService,
    private val database: RedditDatabase,
) : RemoteMediator<Int, Children>() {


    override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, Children>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                remoteKeys.nextKey
            }
        }


        try {
            val response = service.getList(INITIAL_PAGE_LIMIT)
            val redditList = response.body()?.data?.children
            val endOfPaginationReached = redditList?.isEmpty()
            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.redditDao().clearChildren()
                }
                val prevKey = if (page == INITIAL_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached!!) null else page + 1
                val keys = redditList.map {
                    RemoteKeys(redditId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAll(keys)
                database.redditDao().insertAll(redditList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached!!)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Children>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
                ?.let { redditItem ->
                    database.remoteKeysDao().remoteKeysRedditId(redditItem.id)
                }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Children>): RemoteKeys? {

        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { redditItem ->
                    database.remoteKeysDao().remoteKeysRedditId(redditItem.id)
                }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
            state: PagingState<Int, Children>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { redditId ->
                database.remoteKeysDao().remoteKeysRedditId(redditId)
            }
        }
    }
}


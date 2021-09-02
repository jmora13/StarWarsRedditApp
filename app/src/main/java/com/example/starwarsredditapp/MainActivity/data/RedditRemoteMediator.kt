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
import java.io.InvalidObjectException


private var count = 0
private const val PAGE_LIMIT = 10


@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator (
    private val service: RedditService,
    private val database: RedditDatabase,
    private val dao: RemoteKeysDao
) : RemoteMediator<Int, Children>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Children>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.prevKey.plus(1)
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
                val remoteKeys = database.withTransaction {
                    dao.allKeys().lastOrNull()
                }
                if (remoteKeys == null || remoteKeys.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }


        try {
            val response = service.getList(PAGE_LIMIT, page, count)
            val redditList = response.body()?.data?.children
            val endOfPaginationReached = redditList?.isEmpty()
            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.redditDao().clearChildren()
                }
                val prevKey = if (page == null) null else response.body()?.data!!.before
                val nextKey = if (endOfPaginationReached!!) null else response.body()?.data?.after
                val keys = redditList.map {
                    RemoteKeys(redditId = it.data.id, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAll(keys)
                database.redditDao().insertAll(redditList)
                count += PAGE_LIMIT
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached!!)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Children>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { redditItem ->
                    database.remoteKeysDao().remoteKeysRedditId(redditItem.data.id)
                }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
            state: PagingState<Int, Children>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.data?.id?.let { redditId ->
                database.remoteKeysDao().remoteKeysRedditId(redditId)
            }
        }
    }
}


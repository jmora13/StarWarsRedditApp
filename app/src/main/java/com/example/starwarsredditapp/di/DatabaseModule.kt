package com.example.hopskipdrive.di

import android.content.Context
import com.example.starwarsredditapp.CommentsActivity.data.CommentsDao
import com.example.starwarsredditapp.MainActivity.data.RedditDao
import com.example.starwarsredditapp.MainActivity.data.RemoteKeysDao
import com.example.starwarsredditapp.RedditDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context?): RedditDatabase {
        return RedditDatabase.getDatabase(context!!)
    }

    @Provides
    @Singleton
    fun provideRedditDao(redditDatabase: RedditDatabase): RedditDao {
        return redditDatabase.redditDao()
    }

    @Provides
    @Singleton
    fun provideRemoteKeysDao(redditDatabase: RedditDatabase): RemoteKeysDao {
        return redditDatabase.remoteKeysDao()
    }

    @Provides
    @Singleton
    fun provideCommentsDao(redditDatabase: RedditDatabase): CommentsDao {
        return redditDatabase.commentsDao()
    }
}
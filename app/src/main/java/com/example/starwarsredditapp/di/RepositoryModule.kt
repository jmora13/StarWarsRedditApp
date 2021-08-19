package com.example.hopskipdrive.di


import android.content.Context
import com.example.starwarsredditapp.*
import com.example.starwarsredditapp.MainActivity.api.RedditService
import com.example.starwarsredditapp.MainActivity.data.RedditDao
import com.example.starwarsredditapp.MainActivity.data.RedditRepository
import com.example.starwarsredditapp.CommentsActivity.data.CommentsDao
import com.example.starwarsredditapp.CommentsActivity.data.CommentsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    //COMMENT REPOSITORY SINGLETON
    @Provides
    @ViewModelScoped
    fun providesRepo(redditService: RedditService, redditDao: RedditDao, database: RedditDatabase, @ApplicationContext context: Context): RedditRepository {
        return RedditRepository(redditService, redditDao, database, context)
    }

    //COMMENT REPOSITORY SINGLETON
    @Provides
    @ViewModelScoped
    fun providesCommentsRepo(redditService: RedditService, commentsDao: CommentsDao): CommentsRepository {
        return CommentsRepository(redditService, commentsDao)
    }

}
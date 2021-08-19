package com.example.starwarsredditapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Data
import com.example.starwarsredditapp.MainActivity.models.RedditModel.DataX
import com.example.starwarsredditapp.MainActivity.data.RedditDao
import com.example.starwarsredditapp.MainActivity.data.RemoteKeysDao
import com.example.starwarsredditapp.MainActivity.models.RemoteKeys
import com.example.starwarsredditapp.CommentsActivity.data.CommentsDao
import com.example.starwarsredditapp.converters.Converters


@Database(entities = [
    Data::class,
    Children::class,
    DataX::class,
    CommentsChildren::class,
    RemoteKeys::class ], version = 17, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RedditDatabase : RoomDatabase() {

    abstract fun redditDao(): RedditDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun commentsDao(): CommentsDao
    companion object {
        @Volatile
        private var INSTANCE: RedditDatabase? = null

        fun getDatabase(context: Context): RedditDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RedditDatabase::class.java,
                    "reddit_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
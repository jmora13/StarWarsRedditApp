package com.example.starwarsredditapp.MainActivity.data

import androidx.paging.PagingSource
import androidx.room.*
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children

@Dao
interface RedditDao {

    //GET PAGED LISTING
    @Query("SELECT * FROM Children" )
    fun getListing(): PagingSource<Int, Children>

    //INSERT ALL LISTINGS TO DATABASE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(child: List<Children>)

    //DELETE ALL LISTINGS
    @Query("DELETE FROM Children")
    suspend fun clearChildren()

}
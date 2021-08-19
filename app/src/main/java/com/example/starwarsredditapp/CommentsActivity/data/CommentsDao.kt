package com.example.starwarsredditapp.CommentsActivity.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentsDao {

    //GET COMMENTS, CALLED CHILDREN
    @Query("SELECT * FROM comments_children")
    fun getAllComments(): Flow<List<CommentsChildren>>

    //INSERT COMMENT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(child: CommentsChildren)

    //DELETE COMMENTS
    @Query("DELETE FROM comments_children")
    fun deleteComments()
}
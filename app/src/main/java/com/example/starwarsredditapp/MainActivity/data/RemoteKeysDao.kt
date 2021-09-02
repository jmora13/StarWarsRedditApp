package com.example.starwarsredditapp.MainActivity.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsredditapp.MainActivity.models.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM RemoteKeys WHERE redditId = :redditId")
    suspend fun remoteKeysRedditId(redditId: String): RemoteKeys?

    @Query("SELECT * FROM RemoteKeys")
    suspend fun allKeys(): List<RemoteKeys?>

    @Query("DELETE FROM RemoteKeys")
    suspend fun clearRemoteKeys()
}
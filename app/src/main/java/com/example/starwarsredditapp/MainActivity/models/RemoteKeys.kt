package com.example.starwarsredditapp.MainActivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(
        @PrimaryKey
        val redditId: String,
        val prevKey: String?,
        val nextKey: String?
)
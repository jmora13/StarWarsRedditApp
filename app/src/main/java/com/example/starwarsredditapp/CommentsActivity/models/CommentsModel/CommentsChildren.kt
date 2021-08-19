package com.example.starwarsredditapp.CommentsActivity.models.CommentsModel


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
@Entity(tableName = "comments_children")
data class CommentsChildren(
    @PrimaryKey(autoGenerate = true)
    @JsonProperty("id")
    var id: Int,
    @JsonProperty("data")
    val `data`: DataXComments?,
    @JsonProperty("kind")
    val kind: String
)
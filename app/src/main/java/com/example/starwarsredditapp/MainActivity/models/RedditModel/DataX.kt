package com.example.starwarsredditapp.MainActivity.models.RedditModel


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class DataX(
    @JsonProperty("author")
    val author: String?,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("num_comments")
    val numComments: Int?,
    @JsonProperty("preview")
    val preview: Preview?,
    @JsonProperty("score")
    val score: Int,
    @JsonProperty("thumbnail")
    val thumbnail: String?,
    @JsonProperty("thumbnail_height")
    val thumbnailHeight: Int?,
    @JsonProperty("thumbnail_width")
    val thumbnailWidth: Int?,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("url")
    @PrimaryKey
    val url: String,
)
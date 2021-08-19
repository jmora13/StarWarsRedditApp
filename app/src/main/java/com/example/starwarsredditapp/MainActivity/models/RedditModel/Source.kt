package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Source(
    @JsonProperty("height")
    val height: Int,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("width")
    val width: Int
)
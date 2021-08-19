package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Item(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("media_id")
    val mediaId: String
)
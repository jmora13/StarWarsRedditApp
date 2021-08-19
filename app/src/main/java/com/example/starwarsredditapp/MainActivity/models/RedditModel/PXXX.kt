package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class PXXX(
    @JsonProperty("u")
    val u: String,
    @JsonProperty("x")
    val x: Int,
    @JsonProperty("y")
    val y: Int
)
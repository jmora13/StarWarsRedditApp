package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Gildings(
    @JsonProperty("gid_1")
    val gid1: Int
)
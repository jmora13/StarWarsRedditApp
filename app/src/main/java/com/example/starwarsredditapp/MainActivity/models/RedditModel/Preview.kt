package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Preview(
    @JsonProperty("enabled")
    val enabled: Boolean,
    @JsonProperty("images")
    val images: List<Image>
)
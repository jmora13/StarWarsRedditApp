package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class GalleryData(
    @JsonProperty("items")
    val items: List<Item>
)
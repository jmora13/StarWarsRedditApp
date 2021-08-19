package com.example.starwarsredditapp.CommentsActivity.models.CommentsModel


import com.fasterxml.jackson.annotation.JsonProperty

data class CommentsModelItem(
    @JsonProperty("data")
    val `data`: Data?,
    @JsonProperty("kind")
    val kind: String
)
package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RedditModel(
    @JsonProperty("data")
    val `data`: Data?,
    @JsonProperty("kind")
    val kind: String
)
package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Obfuscated(
    @JsonProperty("resolutions")
    val resolutions: List<ResolutionX>,
    @JsonProperty("source")
    val source: SourceX
)
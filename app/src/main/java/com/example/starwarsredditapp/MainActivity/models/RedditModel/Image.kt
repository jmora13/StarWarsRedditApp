package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Image(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("resolutions")
    val resolutions: List<Resolution>?,
    @JsonProperty("source")
    val source: Source?,
)
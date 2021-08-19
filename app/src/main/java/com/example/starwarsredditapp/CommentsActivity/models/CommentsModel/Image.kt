package com.example.starwarsredditapp.CommentsActivity.models.CommentsModel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Image(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("source")
    val source: Source,
)
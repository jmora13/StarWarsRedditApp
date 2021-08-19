package com.example.starwarsredditapp.CommentsActivity.models.CommentsModel


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataXComments(
    @JsonProperty("author")
    val author: String?,
    @JsonProperty("body")
    val body: String?,
    @JsonProperty("children")
    val children: List<String>?,
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("score")
    val score: Int?,
    @JsonProperty("preview")
    val preview: Preview?,
)
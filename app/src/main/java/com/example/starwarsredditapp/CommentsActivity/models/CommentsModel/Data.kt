package com.example.starwarsredditapp.CommentsActivity.models.CommentsModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Data(
    @JsonProperty("after")
    val after: Any?,
    @JsonProperty("before")
    val before: Any?,
    @JsonProperty("children")
    val children: List<CommentsChildren>?,
    @JsonProperty("dist")
    val dist: Any?,
    @JsonProperty("geo_filter")
    val geoFilter: String,
    @JsonProperty("modhash")
    val modhash: String
)
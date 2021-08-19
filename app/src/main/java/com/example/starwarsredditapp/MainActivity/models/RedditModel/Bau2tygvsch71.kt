package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Bau2tygvsch71(
    @JsonProperty("e")
    val e: String,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("m")
    val m: String,
    @JsonProperty("p")
    val p: List<PX>,
    @JsonProperty("s")
    val s: SX,
    @JsonProperty("status")
    val status: String
)
package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Variants(
    @JsonProperty("obfuscated")
    val obfuscated: Obfuscated
)
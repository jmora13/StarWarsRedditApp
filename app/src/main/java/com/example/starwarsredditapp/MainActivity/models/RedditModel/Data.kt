package com.example.starwarsredditapp.MainActivity.models.RedditModel


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class Data(
    @JsonProperty("after")
    @PrimaryKey
    val after: String,
    @JsonProperty("before")
    val before: String?,
    @JsonProperty("children")
    val children: List<Children>,
    @JsonProperty("dist")
    val dist: Int?,
    @JsonProperty("geo_filter")
    val geoFilter: String?,
    @JsonProperty("modhash")
    val modhash: String?
)
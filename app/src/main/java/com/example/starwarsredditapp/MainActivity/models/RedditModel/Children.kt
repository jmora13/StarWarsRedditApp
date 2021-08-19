package com.example.starwarsredditapp.MainActivity.models.RedditModel


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class Children(
    @PrimaryKey(autoGenerate = true)
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("data")
    val `data`: DataX,
    @JsonProperty("kind")
    val kind: String
)
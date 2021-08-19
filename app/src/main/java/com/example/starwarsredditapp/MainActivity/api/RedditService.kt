package com.example.starwarsredditapp.MainActivity.api


import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsModel
import com.example.starwarsredditapp.MainActivity.models.RedditModel.RedditModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    //GET REDDIT LISTING
    @GET("hot.json")
    suspend fun getList(@Query("limit") limit: Int): Response<RedditModel>

    //GET COMMENTS WITH ID
    @GET("comments/{id}")
    suspend fun getComments(@Path("id") id: String): Response<CommentsModel>

}
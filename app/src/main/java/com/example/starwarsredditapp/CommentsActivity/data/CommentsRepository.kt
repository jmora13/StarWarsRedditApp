package com.example.starwarsredditapp.CommentsActivity.data

import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsModel
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import com.example.starwarsredditapp.MainActivity.api.RedditService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsRepository @Inject constructor(private val redditService: RedditService, private val commentsDao: CommentsDao) {
    val commentsList: Flow<List<CommentsChildren>> = commentsDao.getAllComments()

    //INSERT COMMENT ITEM
    private suspend fun insert(child: CommentsChildren){
        commentsDao.insert(child)
    }



    //NETWORK RESPONSE TO GET COMMENT RESPONSE
    suspend fun getCommentResponse(id: String): Response<CommentsModel>? {
        val response = redditService.getComments("$id.json")

        //INSERT RIDES TO DATABASE
        for(i in response.body()?.get(1)!!.data?.children?.indices!!){
            insert(response.body()?.get(1)!!.data?.children?.get(i)!!)
        }
        return response
    }

    suspend fun deleteComments() {
        commentsDao.deleteComments()
    }
}

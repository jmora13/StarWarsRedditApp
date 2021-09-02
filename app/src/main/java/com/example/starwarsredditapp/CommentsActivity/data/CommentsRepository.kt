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
    suspend fun getCommentResponse(id: String): Response<CommentsModel> {
        val response = redditService.getComments("$id.json")

        //INSERT COMMENTS TO DATABASE
        for(i in 0 until (response.body()?.get(1)?.data?.children?.size ?: 0)){
            insert(response.body()?.get(1)?.data?.children?.get(i) ?: break)
        }
        return response
    }

     fun deleteComments() {
        commentsDao.deleteComments()
    }
}

package com.example.starwarsredditapp.CommentsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsModel
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import com.example.starwarsredditapp.CommentsActivity.data.CommentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val repository: CommentsRepository): ViewModel() {

    //LIVE DATA COMMENT LISTING
    val commentsList: LiveData<List<CommentsChildren>> = repository.commentsList.asLiveData()

    //CALL TO REPOSITORY FOR NETWORK CALL
    suspend fun getComments(id: String): Response<CommentsModel>? {
        return repository.getCommentResponse(id)
    }

    suspend fun deleteComments() {
        repository.deleteComments()
    }
}
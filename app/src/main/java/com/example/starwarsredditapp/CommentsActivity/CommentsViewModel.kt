package com.example.starwarsredditapp.CommentsActivity

import android.util.Log
import androidx.lifecycle.*
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsModel
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import com.example.starwarsredditapp.CommentsActivity.data.CommentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val repository: CommentsRepository): ViewModel() {

    //LIVE DATA COMMENT LISTING
    val commentsList: LiveData<List<CommentsChildren>> = repository.commentsList.asLiveData()
    private val _commentsItem = MutableLiveData<List<CommentsChildren>>()
    val commentsItem : LiveData<List<CommentsChildren>> = _commentsItem

    //CALL TO REPOSITORY FOR NETWORK CALL
    fun getComments(id: String?){
        viewModelScope.launch {
            val response = try {
                //GET LIST OF COMMENTS
                deleteComments()
                repository.getCommentResponse(id)
            } catch (e: IOException) {
                Log.d("IOEXCEPTION", e.message.toString())
                return@launch
            } catch (e: HttpException) {
                Log.d("HTTPEXCEPTION", e.stackTrace.toString())
                return@launch
            }
            _commentsItem.postValue(response!!)
        }

    }

    private fun deleteComments() {
        repository.deleteComments()
    }
}
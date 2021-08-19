package com.example.starwarsredditapp.converters

import androidx.room.TypeConverter
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.DataXComments
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children
import com.example.starwarsredditapp.MainActivity.models.RedditModel.DataX
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Preview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
 //TYPE CONVERTER FOR COMPLEX TYPES

    @TypeConverter
    fun restoreChildren(children: String?): List<Children?>? {
        return Gson().fromJson(children, object : TypeToken<List<Children?>?>() {}.type)
    }

    @TypeConverter
    fun saveChildrenString(children: List<Children?>?): String? {
        return Gson().toJson(children)
    }

    @TypeConverter
    fun restoreDataX(data: String?): DataX? {
        return Gson().fromJson(data, object : TypeToken<DataX?>() {}.type)
    }

    @TypeConverter
    fun saveDataX(data: DataX?): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun restoreCommentsChildren(children: String?): List<CommentsChildren?>? {
        return Gson().fromJson(children, object : TypeToken<List<CommentsChildren?>?>() {}.type)
    }

    @TypeConverter
    fun saveCommentsChildrenString(children: List<CommentsChildren?>?): String? {
        return Gson().toJson(children)
    }

    @TypeConverter
    fun restoreDataXComments(data: String?): DataXComments? {
        return Gson().fromJson(data, object : TypeToken<DataXComments?>() {}.type)
    }

    @TypeConverter
    fun saveDataX(data: DataXComments?): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun fromPreview(preview: Preview?): String? {
        return if (preview == null) null else ""
    }

    @TypeConverter
    fun restorePreview(data: String?): Preview? {
        return Gson().fromJson(data, object : TypeToken<Preview?>() {}.type)
    }



}
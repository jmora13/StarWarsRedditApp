package com.example.starwarsredditapp.MainActivity.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwarsredditapp.CommentsActivity.CommentsActivity
import com.example.starwarsredditapp.R
import com.example.starwarsredditapp.MainActivity.models.RedditModel.Children

class RedditListAdapter : PagingDataAdapter<Children, RedditListAdapter.ChildViewHolder>(
    ChildComparator()
) {

    //RECYCLERVIEW FOR REDDIT POSTS
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.fade_scale_animation
        )
        holder.bind(current)
        holder.itemView.setOnClickListener {
            //SEND ID TO MAKE NEW NETWORK CALL
            val i = Intent(holder.itemView.context, CommentsActivity::class.java).apply {
                putExtra("id", current?.data?.id)
            }
            //START COMMENT ACTIVITY
            holder.itemView.context.startActivity(i)
        }
    }


//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        private val score: TextView = itemView.findViewById(R.id.score)
        private val numComments: TextView = itemView.findViewById(R.id.comments)
        private val author: TextView = itemView.findViewById(R.id.author)
        fun bind(child: Children?) {
            title.text = child?.data?.title
            score.text = child?.data?.score.toString()
            numComments.text = child?.data?.numComments.toString()
            author.text = "u/${child?.data?.author}"
            Log.d("Is Preview Null", child?.data?.preview.toString() ?: "null")
                if(child?.data?.preview != null) {
                    Log.d("Thumbnail Height", child?.data?.preview?.images?.get(0)?.resolutions?.get(2)?.url ?: "null")
                    val url = child?.data?.preview?.images?.get(0)?.resolutions?.last()?.url
                    Log.d("Precoded Url", url ?: "null")
                    val encoded = url?.replace("amp;","", false)
                    Log.d("Encoded Url", encoded ?: "null")
                    itemView.apply {
                        Glide.with(this)
                            .load(encoded)
                            .into(thumbnail)
                    }
                } else {
                        itemView.apply {
                            Glide.with(this)
                                .load(child?.data?.thumbnail)
                                .into(thumbnail)
                    }
                }
        }




        companion object {
            fun create(parent: ViewGroup): ChildViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ChildViewHolder(view)
            }
        }
    }

    class ChildComparator : DiffUtil.ItemCallback<Children>() {
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem  == newItem
        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.data.id == newItem.data.id
        }
    }
}




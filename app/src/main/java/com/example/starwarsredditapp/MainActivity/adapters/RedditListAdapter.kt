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
            val postAuthor = "u/${child?.data?.author}"
            author.text = postAuthor
                if(child?.data?.preview != null) {
                    val url = child.data.preview.images[0].resolutions?.last()?.url
                    val encoded = url?.replace("amp;","", false)
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




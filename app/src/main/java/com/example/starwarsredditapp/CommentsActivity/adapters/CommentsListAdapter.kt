package com.example.starwarsredditapp.CommentsActivity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsredditapp.CommentsActivity.models.CommentsModel.CommentsChildren
import com.example.starwarsredditapp.R

class CommentsListAdapter : ListAdapter<CommentsChildren, CommentsListAdapter.ChildViewHolder>(
    ChildComparator()
) {

    //RECYCLERVIEW ADAPTER FOR COMMENTS
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //INDIVIDUAL COMMENT ITEM
        private val comment: TextView = itemView.findViewById(R.id.comment)
        //USER WHO POSTED COMMENT
        private val author: TextView = itemView.findViewById(R.id.author)
        //TOTAL UPVOTES VS DOWNVOTES
        private val commentScore: TextView = itemView.findViewById(R.id.score)

        fun bind(child: CommentsChildren?) {
            comment.text = child?.data?.body
            author.text = "u/" + child?.data?.author
            commentScore.text = child?.data?.score.toString()
        }

        companion object {
            fun create(parent: ViewGroup): ChildViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.comments_recyclerview_item, parent, false)
                return ChildViewHolder(view)
            }
        }
    }

    class ChildComparator : DiffUtil.ItemCallback<CommentsChildren>() {
        override fun areItemsTheSame(oldItem: CommentsChildren, newItem: CommentsChildren): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommentsChildren, newItem: CommentsChildren): Boolean {
            return oldItem.data?.id == newItem.data?.id
        }
    }
}
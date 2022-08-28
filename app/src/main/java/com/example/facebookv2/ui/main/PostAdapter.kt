package com.example.facebookv2.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookv2.R
import com.example.facebookv2.pojo.PostModel


class PostAdapter() :
    androidx.recyclerview.widget.ListAdapter<PostModel, PostAdapter.PostViewHolder>(
        PostModelDiffCallback()
    ) {

    //         RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            titleTV.text = getItem(position).title.toString()
            userTv.text = getItem(position).userId.toString()
            body.text = getItem(position).body.toString()
            itemView.setOnClickListener {
                onItemClickListener?.let { it(getItem(position)) }
            }
        }
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTV = itemView.findViewById<TextView>(R.id.titleTv)
        var userTv = itemView.findViewById<TextView>(R.id.userIdTv)
        var body = itemView.findViewById<TextView>(R.id.bodyTv)
    }

    class PostModelDiffCallback : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(p0: PostModel, p1: PostModel): Boolean {
            return p0 === p1
        }

        override fun areContentsTheSame(p0: PostModel, p1: PostModel): Boolean {
            return p0 == p1
        }
    }

    private var onItemClickListener: ((PostModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (PostModel) -> Unit) {
        onItemClickListener = listener
    }

}
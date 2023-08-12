package com.example.iticourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iticourse.databinding.CommentsLayoutBinding
import com.example.iticourse.databinding.CustomPostsBinding
import com.example.iticourse.model.Comment
import com.example.iticourse.model.Post

class CommentsAdapter(private var commentsList: List<Comment>):RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    inner class CommentsViewHolder(val binding: CommentsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(CommentsLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comments=commentsList[position]
        holder.binding.apply {
            tvPostId.text=comments.postId.toString()
            tvBody.text=comments.body
            tvCommentId.text=comments.id.toString()
            tvEmail.text=comments.email
            tvName.text=comments.name
        }
    }

    override fun getItemCount(): Int {
       return commentsList.size
    }
}
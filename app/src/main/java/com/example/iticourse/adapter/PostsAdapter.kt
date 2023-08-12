package com.example.iticourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iticourse.OnClickListener
import com.example.iticourse.databinding.CustomPostsBinding
import com.example.iticourse.model.Post

class PostsAdapter(
    private var postsList: List<Post>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    inner class PostsViewHolder(val binding: CustomPostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(CustomPostsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = postsList[position]
        holder.binding.apply {
            tvId.text = "id: ${posts.id}"
            tvUserId.text = "postId: ${posts.userId}"
            tvBody.text = posts.body
            btnClickHere.setOnClickListener {
                listener.onClick(posts, position)
            }
        }


    }

    override fun getItemCount(): Int {
        return postsList.size
    }
}
package com.example.iticourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iticourse.databinding.PostsBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    private var postList: List<Post> = ArrayList()

    inner class PostsViewHolder(val binding: PostsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(PostsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = postList[position]
        holder.binding.apply {
            textUsername.text = posts.username
            textPostDate.text = posts.postDate
            textPostText.text = posts.postText
        }
    }

    fun setPosts(posts: List<Post>) {
        postList = posts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
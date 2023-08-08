package com.example.iticourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iticourse.databinding.CustomPostsBinding

class PostsAdapter(
    private var postList: List<Post> = ArrayList(),
    private val listener: OnClickListener
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    inner class PostsViewHolder(val binding: CustomPostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(CustomPostsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = postList[position]
        holder.binding.apply {
            textUsername.text = posts.username
            textPostDate.text = posts.postDate
            textPostText.text = posts.postText
            btnClickHere.setOnClickListener {
                listener.onClick(posts, position)
            }
        }
        Glide.with(holder.itemView).load(posts.image).into(holder.binding.ivPerson)

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
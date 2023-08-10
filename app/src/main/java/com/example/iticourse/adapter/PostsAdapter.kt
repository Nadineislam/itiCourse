package com.example.iticourse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iticourse.OnClickListener
import com.example.iticourse.databinding.CustomPostsBinding
import com.example.iticourse.model.User

class PostsAdapter(
    private var userList: List<User>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    inner class PostsViewHolder(val binding: CustomPostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(CustomPostsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val users = userList[position]
        holder.binding.apply {
            textUsername.text = "${users.firstName} ${users.lastName}"
            textPostDate.text = "${users.id}"
            textPostText.text = users.email
            btnClickHere.setOnClickListener {
                listener.onClick(users, position)
            }
        }
        Glide.with(holder.itemView).load(users.avatar).into(holder.binding.ivPerson)

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
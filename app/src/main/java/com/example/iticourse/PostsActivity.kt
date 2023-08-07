package com.example.iticourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iticourse.databinding.ActivityPostsBinding
import com.example.iticourse.databinding.PostsBinding

class PostsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostsAdapter
    private lateinit var postList: ArrayList<Post>
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PostsAdapter()
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        postList = ArrayList()
        postList.add(Post("User1", "2023-08-01", "This is post 1."))
        postList.add(Post("User2", "2023-08-02", "This is post 2."))
        postList.add(Post("User3", "2023-08-03", "This is post 3."))

        // Update the adapter with the postList
        adapter.setPosts(postList)


    }
}
package com.example.iticourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iticourse.databinding.ActivityPostsBinding


class PostsActivity : AppCompatActivity(),OnClickListener {
    private lateinit var adapter: PostsAdapter
    private lateinit var postList: ArrayList<Post>
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postList = ArrayList()
        postList.add(Post("User1", "2023-08-01", "This is post 1.",R.drawable.ic_baseline_person))
        postList.add(Post("User2", "2023-08-02", "This is post 2.",R.drawable.ic_baseline_girl_24))
        postList.add(Post("User3", "2023-08-03", "This is post 3.",R.drawable.ic_baseline_person_outline))
        adapter = PostsAdapter(postList,this)
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)



    }

    override fun onClick(post: Post, position: Int) {
        val intent=Intent(baseContext,DetailsActivity::class.java)
intent.putExtra("username",post.username)
        intent.putExtra("date",post.postDate)
        intent.putExtra("text",post.postText)
        intent.putExtra("image",post.image)
        startActivity(intent)
    }
}
package com.example.iticourse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iticourse.R
import com.example.iticourse.adapter.CommentsAdapter
import com.example.iticourse.adapter.PostsAdapter
import com.example.iticourse.api.RetrofitClient
import com.example.iticourse.databinding.ActivityCommentsBinding
import com.example.iticourse.model.Comment

class CommentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentsBinding
    lateinit var adapter:CommentsAdapter
    private lateinit var commentsList: ArrayList<Comment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  binding.tvPostId.text=intent.getIntExtra("post_id",0).toString()
//        binding.tvCommentId.text=intent.getIntExtra("comment_id",0).toString()
//        binding.tvEmail.text=intent.getStringExtra("email")
//        binding.tvBody.text=intent.getStringExtra("body")
//        binding.tvName.text=intent.getStringExtra("name")
        commentsList= ArrayList()
        adapter = CommentsAdapter(commentsList)
        binding.rvComments.adapter = adapter
        binding.rvComments.layoutManager = LinearLayoutManager(this@CommentsActivity)
        val retrofit= RetrofitClient.getInstance()
        val postId = intent.getIntExtra("post_id", 0)
        lifecycleScope.launchWhenStarted { val response=retrofit.getComments(postId)
            if(response.isSuccessful){
val comments=response.body()?: listOf()
                commentsList.addAll(comments)
                adapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this@CommentsActivity,"error",Toast.LENGTH_LONG).show()
            }
        }

    }
}
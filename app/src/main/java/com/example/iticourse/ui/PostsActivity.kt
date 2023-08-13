package com.example.iticourse.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iticourse.OnClickListener
import com.example.iticourse.R
import com.example.iticourse.adapter.PostsAdapter
import com.example.iticourse.api.RetrofitClient
import com.example.iticourse.api.UserApi
import com.example.iticourse.databinding.ActivityPostsBinding
import com.example.iticourse.model.Post
import com.example.iticourse.model.User
import retrofit2.Retrofit


class PostsActivity : AppCompatActivity(), OnClickListener {
    private lateinit var adapter: PostsAdapter
    private lateinit var sharedPref :SharedPreferences
    lateinit var retrofit:UserApi
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref=applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
         retrofit=RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/")
        //binding.tvName.text="welcome ${sharedPref.getString("USERNAME","")}"
      binding.btnGetPosts.setOnClickListener {
          lifecycleScope.launchWhenStarted { val response=retrofit.getPostsByUser(binding.etId.text.toString().toInt())
              if(response.isSuccessful){
                  adapter = PostsAdapter(response.body() ?: listOf(),this@PostsActivity)
                  binding.rvPosts.adapter = adapter
                  binding.rvPosts.layoutManager = LinearLayoutManager(this@PostsActivity)
              }
              else{
                  Toast.makeText(this@PostsActivity,"error",Toast.LENGTH_LONG).show()
              }
          } }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_posts_activity,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
              val editor = sharedPref.edit()
                editor.remove("USERNAME")
                editor.remove("PASSWORD")
                editor.putBoolean("IS_LOGIN",false)
                editor.commit()
                startActivity(Intent(this, SplashScreenActivity::class.java))
                finish()
                true
            }
            else -> {
              super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onClick(post: Post, position: Int) {
        lifecycleScope.launchWhenStarted { val response=retrofit.getComments(post.id)
            if(response.isSuccessful){
//                adapter = PostsAdapter(response.body() ?: listOf(),this@PostsActivity)
//                binding.rvPosts.adapter = adapter
//                binding.rvPosts.layoutManager = LinearLayoutManager(this@PostsActivity)
                val comment=response.body()?.get(0)
                val intent=Intent(this@PostsActivity,CommentsActivity::class.java)
                intent.putExtra("post_id",comment?.postId)
                intent.putExtra("comment_id",comment?.id)
                intent.putExtra("email",comment?.email)
                intent.putExtra("body",comment?.body)
                intent.putExtra("name",comment?.name)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@PostsActivity,"error",Toast.LENGTH_LONG).show()
            }
        }
    }

//    override fun onClick(user: User, position: Int) {
//        val intent=Intent(baseContext, DetailsActivity::class.java)
//        intent.putExtra("username",user.firstName)
//        intent.putExtra("date",user.id)
//        intent.putExtra("text",user.email)
//        intent.putExtra("image",user.avatar)
//        startActivity(intent)
//    }
}
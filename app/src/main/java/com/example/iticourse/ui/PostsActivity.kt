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


class PostsActivity : AppCompatActivity(), OnClickListener {
    private lateinit var adapter: PostsAdapter
    private lateinit var postList: ArrayList<Post>
    private lateinit var sharedPref :SharedPreferences
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref=applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvName.text="welcome ${sharedPref.getString("USERNAME","")}"
        postList = ArrayList()
        postList.add(Post("User1", "2023-08-01", "This is post 1.", R.drawable.ic_baseline_person))
        postList.add(Post("User2", "2023-08-02", "This is post 2.", R.drawable.ic_baseline_girl_24))
        postList.add(Post("User3", "2023-08-03", "This is post 3.",
            R.drawable.ic_baseline_person_outline
        ))
        val retrofit=RetrofitClient.getInstance().create(UserApi::class.java)
        lifecycleScope.launchWhenStarted { val response=retrofit.getUser()
        if(response.isSuccessful){
            adapter = PostsAdapter(response.body()?.data ?: listOf(),this@PostsActivity)
            binding.rvPosts.adapter = adapter
            binding.rvPosts.layoutManager = LinearLayoutManager(this@PostsActivity)
        }
            else{
                Toast.makeText(this@PostsActivity,"error",Toast.LENGTH_LONG).show()
            }
        }


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

    override fun onClick(user: User, position: Int) {
        val intent=Intent(baseContext, DetailsActivity::class.java)
        intent.putExtra("username",user.firstName)
        intent.putExtra("date",user.id)
        intent.putExtra("text",user.email)
        intent.putExtra("image",user.avatar)
        startActivity(intent)
    }
}
package com.example.iticourse

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iticourse.databinding.ActivityPostsBinding


class PostsActivity : AppCompatActivity(),OnClickListener {
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
                startActivity(Intent(this,SplashScreenActivity::class.java))
                finish()
                true
            }
            else -> {
              super.onOptionsItemSelected(item)
            }
        }
    }
}
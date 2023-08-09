 package com.example.iticourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.iticourse.databinding.ActivityDetailsBinding
import com.example.iticourse.databinding.ActivitySplashScreenBinding

 class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref=applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
        val isLogged=sharedPref.getBoolean("IS_LOGIN",true)
        Handler().postDelayed(
            Runnable {
if(isLogged){
    startActivity(Intent(this,MainActivity::class.java))
}
                else{
    startActivity(Intent(this,PostsActivity::class.java))
                }
                finish()
            },3000
        )

    }
}
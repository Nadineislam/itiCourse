package com.example.iticourse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iticourse.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("Name")
        val gender = intent.getStringExtra("Gender")
        val sports = intent.getStringExtra("Sports")
        Toast.makeText(
            baseContext,
            "Hello $name you're $gender and your sports are $sports",
            Toast.LENGTH_LONG
        ).show()
        binding.btnClick.setOnClickListener {
            val loginBy = if (binding.rbGoogle.isChecked) {
                "Google"
            } else {
                "Facebook"
            }
            val intent = Intent()
            intent.putExtra("LOGIN_BY", loginBy)
            setResult(2, intent)
            finish()
        }

    }
}
package com.example.iticourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iticourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnClick.setOnClickListener { binding.btnClick.text = binding.etName.text.toString() }
    }
}
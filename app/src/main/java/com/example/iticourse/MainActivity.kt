package com.example.iticourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iticourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener { val name=binding.etUserName.text.toString()
            val selectedGender = when (binding.radioGroup.checkedRadioButtonId) {
                binding.rbFemale.id -> "Female"
                binding.rbMale.id -> "Male"
                else -> ""
            }
            val selectedSports = mutableListOf<String>()
            if (binding.cbFootball.isChecked) {
                selectedSports.add(binding.cbFootball.text.toString())
            }
            if (binding.cbTennis.isChecked) {
                selectedSports.add(binding.cbTennis.text.toString())
            }
            if (binding.cbRunning.isChecked) {
                selectedSports.add(binding.cbRunning.text.toString())
            }

        Toast.makeText(baseContext,"Hello $name ,you're $selectedGender ,and your sports are ${selectedSports.joinToString()}",Toast.LENGTH_LONG).show()}

    }
}
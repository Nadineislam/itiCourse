package com.example.iticourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.iticourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val name = binding.etUserName.text.toString()
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

            // Toast.makeText(baseContext,"Hello $name ,you're $selectedGender ,and your sports are ${selectedSports.joinToString()}",Toast.LENGTH_LONG).show()
            val intent = Intent(baseContext, SecondActivity::class.java)
            intent.putExtra("Name", name)
            intent.putExtra("Gender", selectedGender)
            intent.putExtra("Sports", selectedSports.joinToString())
            startActivityForResult(intent, 100)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            Toast.makeText(baseContext, data?.getStringExtra("LOGIN_BY"), Toast.LENGTH_LONG).show()
        }

    }

}

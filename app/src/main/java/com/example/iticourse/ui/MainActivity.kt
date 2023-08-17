package com.example.iticourse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.iticourse.R
import com.example.iticourse.api.RetrofitClient
import com.example.iticourse.api.UserApi
import com.example.iticourse.databinding.ActivityMainBinding
import com.example.iticourse.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: UserApi
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        retrofit = RetrofitClient.getInstance("https://dummyjson.com")
        binding.btnViewPosts.setOnClickListener {
            startActivity(
                Intent(
                    baseContext,
                    PostsActivity::class.java
                )
            )
        }
        binding.btnLogin.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(userName, password)
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

            Toast.makeText(
                baseContext,
                "Hello $name ,you're $selectedGender ,and your sports are ${selectedSports.joinToString()}",
                Toast.LENGTH_LONG
            ).show()
        }

        viewModel.loginSuccess.observe(this) { loginSuccess ->
            if (loginSuccess) {
                moveToNextActivity()
            } else {
                Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_LONG).show()
            }
        }


//            val intent = Intent(baseContext, SecondActivity::class.java)
//            intent.putExtra("Name", name)
//            intent.putExtra("Gender", selectedGender)
//            intent.putExtra("Sports", selectedSports.joinToString())
//            startActivityForResult(intent, 100)
        }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            Toast.makeText(baseContext, data?.getStringExtra("LOGIN_BY"), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goToSecondActivity -> {
                startActivity(Intent(baseContext, SecondActivity::class.java))
            }
            R.id.exit -> {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Are you sure to exit?")
                    .setPositiveButton("yes") { _, _ ->
                        finish()
                    }.setNegativeButton("No") { dialog, _ ->
                        dialog.cancel()
                    }
                val dialog = dialogBuilder.create()
                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveToNextActivity() {
        val sharedPreferences = applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("USERNAME", binding.etUserName.text.toString())
        editor.putString("PASSWORD", binding.etPassword.text.toString())
        editor.putBoolean("IS_LOGIN", true)
        editor.commit()
        startActivity(Intent(baseContext, PostsActivity::class.java))
    }

}

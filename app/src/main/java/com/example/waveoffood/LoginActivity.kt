package com.example.waveoffood

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waveoffood.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.createAccountTextView.setOnClickListener{
            val intent = Intent(this@LoginActivity, SignActivity::class.java)
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener{
            val intent = Intent(this@LoginActivity,LocationActivity::class.java)
            startActivity(intent)
        }
    }
}
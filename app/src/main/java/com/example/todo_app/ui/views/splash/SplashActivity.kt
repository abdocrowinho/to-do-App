package com.example.todo_app.ui.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_app.R
import com.example.todo_app.databinding.ActivitySplashBinding
import com.example.todo_app.ui.views.MainActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToMainScreen()

    }

    private fun navigateToMainScreen() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed( {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        },2000)
    }
}
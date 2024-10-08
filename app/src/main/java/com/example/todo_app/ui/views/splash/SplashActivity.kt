package com.example.todo_app.ui.views.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.todo_app.R
import com.example.todo_app.base.BaseActivity
import com.example.todo_app.constants.Strings
import com.example.todo_app.databinding.ActivitySplashBinding
import com.example.todo_app.ui.views.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    var sharedPreferences:SharedPreferences?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(Strings.preferences , Context.MODE_PRIVATE)
        val islight = sharedPreferences?.getBoolean(Strings.isLight,true)
        if (islight!!) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        navigateToMainScreen()
    }
    override fun getLayoutId(): Int {
return R.layout.activity_splash   }

    private fun navigateToMainScreen() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed( {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        },2000)
    }
}
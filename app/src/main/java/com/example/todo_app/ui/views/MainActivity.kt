package com.example.todo_app.ui.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.todo_app.R
import com.example.todo_app.databinding.ActivityMainBinding
import com.example.todo_app.ui.views.fragments.MainFragment
import com.example.todo_app.ui.views.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.bottomNavBar.setOnItemSelectedListener {
    when (it.itemId) {
        R.id.settings_Fragment -> {
          showFragment(SettingsFragment())
        }R.id.list_fragment -> {
            showFragment(MainFragment())
        }


    }

    return@setOnItemSelectedListener true
}
binding.bottomNavBar.selectedItemId = R.id.list_fragment

    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.containerFragment.id, fragment).commit()
    }


}
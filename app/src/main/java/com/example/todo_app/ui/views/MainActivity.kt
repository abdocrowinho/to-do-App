package com.example.todo_app.ui.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.todo_app.R
import com.example.todo_app.base.BaseActivity
import com.example.todo_app.databinding.ActivityMainBinding
import com.example.todo_app.ui.views.fragments.addTask_fragment.addTaskFragment
import com.example.todo_app.ui.views.fragments.main_fragment.MainFragment
import com.example.todo_app.ui.views.fragments.settings_fragment.SettingsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings_Fragment -> {
                    showFragment(SettingsFragment())
                }

                R.id.list_fragment -> {
                    showFragment(MainFragment())
                }


            }

            return@setOnItemSelectedListener true
        }
        binding.bottomNavBar.selectedItemId = R.id.list_fragment

        binding.fabAdd.setOnClickListener {
            val addTask = addTaskFragment()
            addTask.show(supportFragmentManager, "")
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.containerFragment.id, fragment).commit()
    }
}
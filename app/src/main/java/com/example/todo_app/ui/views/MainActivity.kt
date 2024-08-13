package com.example.todo_app.ui.views

import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.todo_app.R
import com.example.todo_app.databinding.ActivityMainBinding
import com.example.todo_app.ui.views.fragments.addTask_fragment.addTaskFragment
import com.example.todo_app.ui.views.fragments.main_fragment.MainFragment
import com.example.todo_app.ui.views.fragments.settings_fragment.SettingsFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var isClick = false
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
        binding.fabAddTask.setOnClickListener{
            showBottomSheetFragment(addTaskFragment())

        }
binding.bottomNavBar.selectedItemId = R.id.list_fragment

    }

private fun fabClick(){
    isClick=!isClick
    if(isClick){
        binding.fabAddTask.setImageResource(R.drawable.ic_check)
        showBottomSheetFragment(addTaskFragment())
        } else{
        binding.fabAddTask.setImageResource(R.drawable.baseline_add_24)
    }


}
    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.content.containerFragment.id, fragment).commit()
    }
    private  fun showBottomSheetFragment(fragment: Fragment){
        val addTaskFragment = addTaskFragment()
        addTaskFragment.show(supportFragmentManager,"")
    }
   


}
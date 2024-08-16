package com.example.todo_app.ui.views.fragments.main_fragment

import android.os.Bundle
import android.view.View
import com.example.todo_app.R
import com.example.todo_app.base.BaseFragment
import com.example.todo_app.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_main




}

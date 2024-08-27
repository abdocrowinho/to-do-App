package com.example.todo_app.ui.views.fragments.settings_fragment

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.example.todo_app.R
import com.example.todo_app.base.BaseFragment
import com.example.todo_app.constants.Strings
import com.example.todo_app.databinding.FragmentSettingsBinding
import com.example.todo_app.ui.views.MainActivity
import java.util.Locale

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    private var sharedPreferences: SharedPreferences? = null
    private var islight: Boolean? = null
    private var isEnglish:Boolean?=null
    private var  modeNow : String?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSharedPref()
if (isEnglish!!){
    binding.lanAct.setHint(R.string.english)
}else{
    binding.lanAct.setHint(R.string.arabic)
}
        if (islight!!){
            binding.actMode.setHint(R.string.light)
        }else{
            binding.actMode.setHint(R.string.night)

        }
        super.onViewCreated(view, savedInstanceState)
        binding.lanAct.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, pos, l ->
            val selcetedItem = adapterView.getItemAtPosition(pos).toString()
            val editor = sharedPreferences?.edit()

            if (selcetedItem.equals("english")) {

                editor?.putBoolean(Strings.isEnglish,true)?.commit()
                binding.lanAct.setHint(R.string.english)
                LocaleHelper.setLocale(requireContext(), "er")
                activity?.recreate()
                Log.e("LanNow",isEnglish.toString())
            } else if (selcetedItem.equals("arabic")) {
                editor?.putBoolean(Strings.isEnglish,false)?.commit()
                Log.e("LanNow",isEnglish.toString())
                binding.lanAct.setHint(R.string.arabic)
                LocaleHelper.setLocale(requireContext(), "ar")

                activity?.recreate()


            }

        }

        )
        binding.actMode.setOnItemClickListener { adapterView, view, pos, l ->
            val selectedItem = adapterView.getItemAtPosition(pos).toString()
            val editor = sharedPreferences?.edit()

            if (selectedItem == getString(R.string.light)) {
                    editor?.putBoolean(Strings.isLight, true)?.commit()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                    activity?.recreate()
                    Log.e("is light " , islight.toString())
                } else if (selectedItem == getString(R.string.night)) {
                    editor?.putBoolean(Strings.isLight, false)?.commit()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                    activity?.recreate()
                    Log.e("is light " , islight.toString())
                }else{
                    Log.e("selectedItem",selectedItem)
                }



        }

    }






    private fun initSharedPref(): SharedPreferences? {
        sharedPreferences = activity?.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        islight = sharedPreferences?.getBoolean(Strings.isLight,true )
        isEnglish = sharedPreferences?.getBoolean(Strings.isEnglish,true)
        return sharedPreferences
    }

    override fun getLayoutId(): Int = R.layout.fragment_settings


}




package com.example.videoapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.R
import com.example.videoapp.databinding.MainActivityBinding
import com.example.videoapp.utils.replaceFragment
import com.example.videoapp.view.fragment.ItemTypeApp
import com.example.videoapp.view.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(R.layout.main_activity)

        binding.toggleButtonGroup.check(R.id.buttonImage)
        binding.toggleButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when (checkedId) {
                R.id.buttonImage -> replaceFrag(ItemTypeApp.IMAGE)
                R.id.buttonVideos -> replaceFrag(ItemTypeApp.VIDEO)
            }
        }
        replaceFrag(ItemTypeApp.VIDEO)
    }

    fun replaceFrag(feedType: ItemTypeApp) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment(feedType)).commitNow()
    }
}
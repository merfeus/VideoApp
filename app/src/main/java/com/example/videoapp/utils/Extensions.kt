package com.example.videoapp.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.videoapp.R
import com.example.videoapp.view.fragment.ItemTypeApp
import com.example.videoapp.view.fragment.MainFragment


fun AppCompatActivity.replaceFragment(type: ItemTypeApp) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment(type))
        .commitNow()
}

fun AppCompatActivity.hideKeyboard() {
    val imm = window.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}





package com.example.videoapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.videoapp.view.activity.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TesteSerach {

    private lateinit var stringToBeTyped: String


    @get:Rule

    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        stringToBeTyped = "Praias"
    }

    @After
    fun tearDown(){

    }
}
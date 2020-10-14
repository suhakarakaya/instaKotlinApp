package com.suhakarakaya.instakotlin.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.BottomNavigationHelper
import kotlinx.android.synthetic.main.activity_home.*

class ProfileActivity : AppCompatActivity() {
    private val ACTVITY_NO = 4
    private val TAG = "ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupNavigationView()
    }


    fun setupNavigationView() {

        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationHelper.setupNavigation(this, bottomNavigationView)
        var menu = bottomNavigationView.menu
        var menuItem = menu.getItem(ACTVITY_NO)
        menuItem.setChecked(true)

    }
}
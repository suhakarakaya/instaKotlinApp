package com.suhakarakaya.instakotlin.News

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.BottomNavigationHelper
import kotlinx.android.synthetic.main.activity_home.*

class NewsActivity : AppCompatActivity() {
    private val ACTVITY_NO = 3
    private val TAG = "NewsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //setupNavigationView()
    }


    fun setupNavigationView() {

        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationHelper.setupNavigation(this, bottomNavigationView)
        var menu = bottomNavigationView.menu
        var menuItem = menu.getItem(ACTVITY_NO)
        menuItem.setChecked(true)

    }
}
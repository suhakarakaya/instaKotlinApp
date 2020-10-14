package com.suhakarakaya.instakotlin.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.BottomNavigationHelper
import com.suhakarakaya.instakotlin.utils.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val ACTVITY_NO = 0
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupNavigationView()
        setupHomeViewPager()
    }


    fun setupNavigationView() {

        BottomNavigationHelper.setupBottomNavigationView(bottomNavigationView)
        BottomNavigationHelper.setupNavigation(this, bottomNavigationView)
        var menu = bottomNavigationView.menu
        var menuItem = menu.getItem(ACTVITY_NO)
        menuItem.setChecked(true)

    }


    fun setupHomeViewPager() {

        var homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        homePagerAdapter.addFragment(CameraFragment())
        homePagerAdapter.addFragment(HomeFragment())
        homePagerAdapter.addFragment(MessageFragment())



        homeViewPager.adapter = homePagerAdapter
        homeViewPager.currentItem = 1

    }
}
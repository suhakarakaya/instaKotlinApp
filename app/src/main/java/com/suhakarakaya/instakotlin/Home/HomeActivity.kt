package com.suhakarakaya.instakotlin.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nostra13.universalimageloader.core.ImageLoader
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.BottomNavigationHelper
import com.suhakarakaya.instakotlin.utils.HomePagerAdapter
import com.suhakarakaya.instakotlin.utils.UniveralImageLoader
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val ACTVITY_NO = 0
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initImageLoader()
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


    private fun initImageLoader() {

        var univeralImageLoader = UniveralImageLoader(this)
        ImageLoader.getInstance().init(univeralImageLoader.config)


    }
}
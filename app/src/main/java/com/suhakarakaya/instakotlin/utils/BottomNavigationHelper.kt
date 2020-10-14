package com.suhakarakaya.instakotlin.utils

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.suhakarakaya.instakotlin.Home.HomeActivity
import com.suhakarakaya.instakotlin.News.NewsActivity
import com.suhakarakaya.instakotlin.Profile.ProfileActivity
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.Search.SearchActivity
import com.suhakarakaya.instakotlin.Share.ShareActivity
import com.suhakarakaya.instakotlin.R.menu.bottom_navigation_menu


class BottomNavigationHelper {

    companion object {

        fun setupBottomNavigationView(bottomNavigationViewEx: BottomNavigationViewEx) {
            bottomNavigationViewEx.enableAnimation(false)
            bottomNavigationViewEx.enableItemShiftingMode(false)
            bottomNavigationViewEx.enableShiftingMode(false)
            bottomNavigationViewEx.enableShiftingMode(0,false)
            bottomNavigationViewEx.setTextVisibility(false)

        }


        fun setupNavigation(context: Context, bottomNavigationViewEx: BottomNavigationViewEx) {


            bottomNavigationViewEx.onNavigationItemSelectedListener =
                object : BottomNavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                        when (p0.itemId) {
                            R.id.ic_home -> {
                                val intent = Intent(context, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                return true
                            }
                            R.id.ic_search -> {
                                val intent = Intent(context, SearchActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                return true
                            }
                            R.id.ic_share -> {
                                val intent = Intent(context, ShareActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                return true
                            }
                            R.id.ic_news -> {
                                val intent = Intent(context, NewsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)
                                return true
                            }
                            R.id.ic_profile -> {
                                val intent = Intent(context, ProfileActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                context.startActivity(intent)

                                return true
                            }

                        }
                        return false

                    }

                }

        }
    }


}
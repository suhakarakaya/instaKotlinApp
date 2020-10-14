package com.suhakarakaya.instakotlin.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var mFrsgmentList: ArrayList<Fragment> = ArrayList()


    override fun getCount(): Int {
        return mFrsgmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFrsgmentList.get(position)
    }

    fun addFragment(fragamen: Fragment) {
        mFrsgmentList.add(fragamen)
    }


}
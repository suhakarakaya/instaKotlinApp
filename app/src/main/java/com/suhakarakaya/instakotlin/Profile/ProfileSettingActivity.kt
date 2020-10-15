package com.suhakarakaya.instakotlin.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.BottomNavigationHelper
import kotlinx.android.synthetic.main.activity_profile_setting.*

class ProfileSettingActivity : AppCompatActivity() {

    private val ACTVITY_NO = 4
    private val TAG = "ProfileActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setting)
        setupNavigationView()
        setupToolBar()
        fragmentNavigations()

    }

    private fun fragmentNavigations() {
        txt_DuzenleHesapAyari.setOnClickListener {
            profile_settings_root.visibility = View.GONE
            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_profilSettingsContainer, ProfileEditFragment())
            transaction.addToBackStack("profileSettingsFragmentEklendi")
            transaction.commit()
        }
        txt_cikisYap.setOnClickListener {
            profile_settings_root.visibility = View.GONE
            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_profilSettingsContainer, SignOutFragment())
            transaction.addToBackStack("signOutFragmentEklendi")
            transaction.commit()
        }
    }

    private fun setupToolBar() {

        img_back_settings.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onBackPressed() {
        profile_settings_root.visibility = View.VISIBLE
        super.onBackPressed()

    }

    fun setupNavigationView() {

        BottomNavigationHelper.setupBottomNavigationView(bottom_navigation_profile_settings)
        BottomNavigationHelper.setupNavigation(this, bottom_navigation_profile_settings)
        var menu = bottom_navigation_profile_settings.menu
        var menuItem = menu.getItem(ACTVITY_NO)
        menuItem.setChecked(true)

    }
}
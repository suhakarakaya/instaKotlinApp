package com.suhakarakaya.instakotlin.Profile

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl
import android.view.View
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.BottomNavigationHelper
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    private val ACTVITY_NO = 4
    private val TAG = "ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupNavigationView()
        setupToolBar()
    }

    private fun setupToolBar() {
        img_settings.setOnClickListener {
            var intent = Intent(
                this,
                ProfileSettingActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        txt_ProfilSettings.setOnClickListener {

            cl_profileRoot.visibility = View.GONE
            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_profileContaniner, ProfileEditFragment())
            transaction.addToBackStack("profileSettingsFragmentEklendi")
            transaction.commit()

        }
    }

    override fun onBackPressed() {
        cl_profileRoot.visibility=View.VISIBLE
        super.onBackPressed()
    }


    fun setupNavigationView() {

        BottomNavigationHelper.setupBottomNavigationView(bottom_navigationVİew)
        BottomNavigationHelper.setupNavigation(this, bottom_navigationVİew)
        var menu = bottom_navigationVİew.menu
        var menuItem = menu.getItem(ACTVITY_NO)
        menuItem.setChecked(true)

    }
}
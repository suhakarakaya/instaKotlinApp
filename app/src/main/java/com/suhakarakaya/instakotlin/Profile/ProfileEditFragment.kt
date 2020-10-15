package com.suhakarakaya.instakotlin.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostra13.universalimageloader.core.ImageLoader
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.UniveralImageLoader
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.fragment_profile_edit.view.*

class ProfileEditFragment : Fragment() {

    lateinit var circleProfileImageFragment: CircleImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater!!.inflate(R.layout.fragment_profile_edit, container, false)
        circleProfileImageFragment = view.findViewById(R.id.img_profile_circle)
        setupPhotoPicture()


        view.imgClose.setOnClickListener {

            activity?.let {
                it!!.onBackPressed()
            }

        }
        return view
    }




    private fun setupPhotoPicture() {
        var imgURL = "orig00.deviantart.net/67cd/f/2012/309/8/c/android_icon_by_gabrydesign-d4m7he9.png"
        UniveralImageLoader.setImage(imgURL, circleProfileImageFragment, null, "https://")
    }


}
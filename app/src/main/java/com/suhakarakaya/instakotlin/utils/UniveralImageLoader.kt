package com.suhakarakaya.instakotlin.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import com.suhakarakaya.instakotlin.R

class UniveralImageLoader(val mContext: Context) {

    val config: ImageLoaderConfiguration
        get() {

            val defaultOptions = DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(FadeInBitmapDisplayer(400)).build()

            return ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024).build()

        }


    companion object {
        private val defaultImage = R.drawable.profile_selected

        fun setImage(
            imgURL: String,
            imageView: ImageView,
            mProgressBar: ProgressBar?,
            ilkKısım: String
        ) {

            val imageLoader = ImageLoader.getInstance()
            imageLoader.displayImage(ilkKısım + imgURL, imageView, object : ImageLoadingListener {
                override fun onLoadingStarted(imageUri: String?, view: View?) {
                    if (mProgressBar != null) {
                        mProgressBar.visibility = View.VISIBLE
                    }
                }

                override fun onLoadingFailed(
                    imageUri: String?,
                    view: View?,
                    failReason: FailReason?
                ) {
                    if (mProgressBar != null) {
                        mProgressBar.visibility = View.VISIBLE
                    }
                }

                override fun onLoadingComplete(
                    imageUri: String?,
                    view: View?,
                    loadedImage: Bitmap?
                ) {
                    if (mProgressBar != null) {
                        mProgressBar.visibility = View.GONE
                    }
                }

                override fun onLoadingCancelled(imageUri: String?, view: View?) {
                    if (mProgressBar != null) {
                        mProgressBar.visibility = View.VISIBLE
                    }
                }

            })


        }

    }


}
package com.app.videopreloading.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.videopreloading.R
import com.app.videopreloading.callback.HomeScreenCallback
import com.app.videopreloading.service.VideoPreLoadingService
import com.app.videopreloading.utility.Constants
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), View.OnClickListener {
    private var homeScreenCallback: HomeScreenCallback? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private var videoList = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        videoList.add("https://dev-youknow-video-bucket.s3.ap-southeast-1.amazonaws.com/3d8da76c-c504-4731-82c9-56ac386e60fb_2024-01-12-11:29:43:60403717756614012145122.mp4")

        videoList.add("https://dev-youknow-video-bucket.s3.ap-southeast-1.amazonaws.com/3d8da76c-c504-4731-82c9-56ac386e60fb_2024-01-12-11:29:43:60403717756614012145122.mp4")

        buttonPlayVideo1.setOnClickListener(this)

        buttonPlayVideo2.setOnClickListener(this)

        startPreLoadingService()
    }

    private fun startPreLoadingService() {
        val preloadingServiceIntent = Intent(context, VideoPreLoadingService::class.java)
        preloadingServiceIntent.putStringArrayListExtra(Constants.VIDEO_LIST, videoList)
        context?.startService(preloadingServiceIntent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            homeScreenCallback = context as HomeScreenCallback
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDetach() {
        super.onDetach()
        homeScreenCallback = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonPlayVideo1 -> {
                homeScreenCallback?.openVideoPlayScreen(videoList[0])
            }
            R.id.buttonPlayVideo2 -> {
                homeScreenCallback?.openVideoPlayScreen(videoList[1])
            }
        }
    }
}
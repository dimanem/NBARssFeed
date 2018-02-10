package com.dimanem.android.nba.rssreader.ui

import android.support.v4.app.FragmentManager
import com.dimanem.android.nba.rssreader.MainActivity
import com.dimanem.android.nba.rssreader.R
import javax.inject.Inject

/**
 * Created by dimanemets on 10/02/2018.
 */
class NavigationController {

    private val containerId: Int
    private val fragmentManager: FragmentManager

    @Inject
    constructor(mainActivity: MainActivity) {
        containerId = R.id.container
        fragmentManager = mainActivity.supportFragmentManager
    }

    fun navigateToRSS(topic: String) {
        val rssFragment = NBARssFragment.create(topic)
        fragmentManager.beginTransaction()
                .replace(containerId, rssFragment)
                .commitAllowingStateLoss()
    }
}

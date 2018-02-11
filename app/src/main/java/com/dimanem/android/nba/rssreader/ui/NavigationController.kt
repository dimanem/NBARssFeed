package com.dimanem.android.nba.rssreader.ui

import android.support.v4.app.FragmentManager
import com.dimanem.android.nba.rssreader.MainActivity
import com.dimanem.android.nba.rssreader.R
import com.dimanem.android.nba.rssreader.ui.rss.RssFragment
import javax.inject.Inject

/**
 * Created by dimanemets on 10/02/2018.
 */
class NavigationController {

    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager

    @Inject
    constructor(mainActivity: MainActivity) {
        fragmentManager = mainActivity.supportFragmentManager
    }

    fun navigateToRSS(topic: String) {
        val rssFragment = RssFragment.create(topic)
        val tag = "rss" + "/" + topic
        fragmentManager.beginTransaction()
                .replace(containerId, rssFragment, tag)
                .commitAllowingStateLoss()
    }
}

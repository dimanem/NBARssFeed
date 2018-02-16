package com.dimanem.android.nba.rssreader.api

import android.arch.lifecycle.LiveData
import com.dimanem.android.nba.rssreader.vo.RSS
import retrofit2.http.GET

/**
 * Created by dimanemets on 09/02/2018.
 */
interface RssService {

    @GET("rss/nba_rss.xml")
    fun getMainRssFeed(): LiveData<ApiResponse<RSS>>
}

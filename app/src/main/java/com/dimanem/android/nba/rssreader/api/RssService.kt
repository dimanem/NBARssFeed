package com.dimanem.android.nba.rssreader.api

import android.arch.lifecycle.LiveData
import com.dimanem.android.nba.rssreader.vo.RSS
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by dimanemets on 09/02/2018.
 */
interface RssService {

    @GET("{channelId}")
    fun getRssFeedNotEncoded(@Path("channelId", encoded = true) topic: String):
            LiveData<ApiResponse<RSS>>
}

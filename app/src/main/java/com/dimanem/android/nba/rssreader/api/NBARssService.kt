package com.dimanem.android.nba.rssreader.api

import com.dimanem.android.nba.rssreader.vo.RSS
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by dimanemets on 09/02/2018.
 */
interface NBARssService {

    @GET("rss/nba_rss.xml")
    public abstract fun getAllFeeds(): Observable<RSS>
}

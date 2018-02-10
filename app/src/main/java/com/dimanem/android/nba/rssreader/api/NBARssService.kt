package com.dimanem.android.nba.rssreader.api

import com.dimanem.android.nba.rssreader.vo.RSS
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by dimanemets on 09/02/2018.
 */
interface NBARssService {

    @GET("{topic}")
    public abstract fun getRssFeedNotEncoded(@Path("topic", encoded = true) topic: String): Observable<RSS>
}

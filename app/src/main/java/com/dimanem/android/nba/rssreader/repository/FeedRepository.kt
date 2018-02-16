package com.dimanem.android.nba.rssreader.repository

import android.arch.lifecycle.LiveData
import com.dimanem.android.nba.rssreader.AppExecutors
import com.dimanem.android.nba.rssreader.api.ApiResponse
import com.dimanem.android.nba.rssreader.api.RssService
import com.dimanem.android.nba.rssreader.db.*
import com.dimanem.android.nba.rssreader.vo.RSS
import com.dimanem.android.nba.rssreader.vo.Resource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by dimanemets on 10/02/2018.
 */
@Singleton
class FeedRepository() {

    lateinit var appExecutors: AppExecutors
    lateinit var rssDb: RssDB
    lateinit var rssService: RssService
    lateinit var itemDao: ItemDao

    @Inject
    constructor(appExecutors: AppExecutors, rssDb: RssDB,
                rssService: RssService, itemDao: ItemDao) : this() {
        this.appExecutors = appExecutors
        this.rssDb = rssDb
        this.rssService = rssService
        this.itemDao = itemDao
    }

    fun loadItems() : LiveData<Resource<List<ItemEntity>>> {
        return object : NetworkBoundResource<List<ItemEntity>, RSS>(appExecutors) {

            override fun shouldFetch(data: List<ItemEntity>?): Boolean {
                // Null or empty ...
                return data?.size == 0
            }

            override fun loadFromDb(): LiveData<List<ItemEntity>> {
                return itemDao.getItems()
            }

            override fun createCall(): LiveData<ApiResponse<RSS>> {
                Timber.v("Items: createCall")
                return rssService.getMainRssFeed()
            }

            override fun saveCallResult(item: RSS?) {
                Timber.v("Items: saveCallResult-> " + item?.toString())
                saveToDB(item)
            }

        }.asLiveData()
    }

    private fun saveToDB(rss: RSS?) {
        if (rss != null) {
            val channel = rss.channel
            if (channel != null) {
                // Save channel to db
                val items = channel.items
                // Save items to db
                if (items != null) {
                    itemDao.insert(items.map { item ->
                        ItemEntity(item)
                    })
                }
            }
        }
    }
}

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
class RssRepository() {

    lateinit var appExecutors: AppExecutors
    lateinit var rssDb: RssDB
    lateinit var rssService: RssService
    lateinit var channelDao: ChannelDao
    lateinit var itemDao: ItemDao

    @Inject
    constructor(appExecutors: AppExecutors, rssDb: RssDB,
                rssService: RssService, channelDao: ChannelDao, itemDao: ItemDao) : this() {
        this.appExecutors = appExecutors
        this.rssDb = rssDb
        this.rssService = rssService
        this.channelDao = channelDao
        this.itemDao = itemDao
    }

    fun loadChannel(channelId: String): LiveData<Resource<ChannelEntity>> {
        return object : NetworkBoundResource<ChannelEntity, RSS>(appExecutors) {

            override fun saveCallResult(item: RSS?) {
                Timber.v("Channel: saveCallResult -> " + item?.toString())
                saveRssResult(channelId, item)
            }

            override fun shouldFetch(data: ChannelEntity?): Boolean {
                val shouldFetch = data == null
                Timber.v("Channel: shouldFetch -> " + shouldFetch)
                return shouldFetch
            }

            override fun loadFromDb(): LiveData<ChannelEntity> {
                Timber.v("Channel: loadFromDb -> " + channelId)
                return channelDao.getChannelById(channelId)
            }

            override fun createCall(): LiveData<ApiResponse<RSS>> {
                Timber.v("Channel: createCall -> " + channelId)
               return rssService.getRssFeedNotEncoded(channelId)
            }

        }.asLiveData()
    }

    fun loadItems(channelId: String) : LiveData<Resource<List<ItemEntity>>> {
        return object : NetworkBoundResource<List<ItemEntity>, RSS>(appExecutors) {

            override fun shouldFetch(data: List<ItemEntity>?): Boolean {
                val shouldFetch = data == null
                Timber.v("Items: shouldFetch -> " + shouldFetch)
                return shouldFetch
            }

            override fun loadFromDb(): LiveData<List<ItemEntity>> {
                Timber.v("Items: loadFromDb -> " + channelId)
                return itemDao.getItemsForChannel(channelId)
            }

            override fun createCall(): LiveData<ApiResponse<RSS>> {
                Timber.v("Items: createCall")
                return rssService.getRssFeedNotEncoded(channelId)
            }

            override fun saveCallResult(item: RSS?) {
                Timber.v("Items: saveCallResult-> " + item?.toString())
                saveRssResult(channelId, item)
            }

        }.asLiveData()
    }

    private fun saveRssResult(channelId: String, rss: RSS?) {
        if (rss != null) {
            val channel = rss.channel
            if (channel != null) {
                // Save channel to db
                channelDao.insert(ChannelEntity(channelId, channel))
                val items = channel.items
                // Save items to db
                if (items != null) {
                    itemDao.insert(items.map { item ->
                        ItemEntity(channelId, item)
                    })
                }
            }
        }
    }
}

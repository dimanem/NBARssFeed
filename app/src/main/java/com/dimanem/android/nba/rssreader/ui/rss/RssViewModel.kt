package com.dimanem.android.nba.rssreader.ui.rss

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.dimanem.android.nba.rssreader.db.ChannelEntity
import com.dimanem.android.nba.rssreader.db.ItemEntity
import com.dimanem.android.nba.rssreader.repository.RssRepository
import com.dimanem.android.nba.rssreader.vo.Resource
import javax.inject.Inject

/**
 * Created by dimanemets on 11/02/2018.
 */
class RssViewModel : ViewModel {

    var channelId: MutableLiveData<String> = MutableLiveData()

    private var channel: LiveData<Resource<ChannelEntity>>? = null
    private var items: LiveData<Resource<List<ItemEntity>>>? = null

    @Inject
    constructor(rssRepository: RssRepository) {
        channel = Transformations.switchMap(channelId) {
            if (it.isNotEmpty()) {
                rssRepository.loadChannel(it)
            } else {
                MutableLiveData()
            }
        }
        items = Transformations.switchMap(channelId) {
            if (it.isNotEmpty()) {
                rssRepository.loadItems(it)
            } else {
                MutableLiveData()
            }
        }
    }

    fun setChannelId(channelId: String?) {
        this.channelId.value = channelId
    }

    fun getChannel(): LiveData<Resource<ChannelEntity>>? {
        return this.channel
    }

    fun getItems() : LiveData<Resource<List<ItemEntity>>>? {
        return this.items
    }
}

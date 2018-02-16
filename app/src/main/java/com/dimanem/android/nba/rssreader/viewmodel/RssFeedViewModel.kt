package com.dimanem.android.nba.rssreader.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.dimanem.android.nba.rssreader.db.ItemEntity
import com.dimanem.android.nba.rssreader.repository.FeedRepository
import com.dimanem.android.nba.rssreader.vo.Resource
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dimanemets on 11/02/2018.
 */
class RssFeedViewModel : ViewModel {

    var topicId: MutableLiveData<String> = MutableLiveData()

    private var items: LiveData<Resource<List<ItemEntity>>>? = null

    @Inject
    constructor(feedRepository: FeedRepository) {
        items = feedRepository.loadItems()

        // This means that if someone called setTopicId(newValue)
        // A new loading would start
//        topicId = Transformations.switchMap(channelId) {
//            if (it.isNotEmpty()) {
//                rssRepository.loadChannel(it)
//            } else {
//                MutableLiveData()
//            }
//        }
    }

    fun setTopicId(channelId: String?) {
        this.topicId.value = channelId
    }

    fun getItems() : LiveData<Resource<List<ItemEntity>>>? {
        return this.items
    }
}

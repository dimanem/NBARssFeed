package com.dimanem.android.nba.rssreader.ui.rss

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimanem.android.nba.rssreader.R
import com.dimanem.android.nba.rssreader.db.ChannelEntity
import com.dimanem.android.nba.rssreader.db.ItemEntity
import com.dimanem.android.nba.rssreader.di.Injectable
import com.dimanem.android.nba.rssreader.vo.Resource
import com.dimanem.android.nba.rssreader.vo.Status
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dimanemets on 10/02/2018.
 */
class RssFragment : Fragment(), Injectable {

    var topic: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var rssViewModel: RssViewModel? = null

    companion object {
        fun create(topic: String): RssFragment {
            val rssFragment = RssFragment()
            val args = Bundle()
            args.putString(RSS_TOPIC_PARAM_KEY, topic)
            rssFragment.arguments = args
            return rssFragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rssViewModel = ViewModelProviders.of(this, viewModelFactory).get(RssViewModel::class.java)

        val viewModel = rssViewModel!!
        val args = arguments
        if (args != null && args.containsKey(RSS_TOPIC_PARAM_KEY)) {
            topic = args.getString(RSS_TOPIC_PARAM_KEY)
            viewModel.setChannelId(topic)
        }

        Timber.i("The topic is: " + topic)

        val channel = viewModel.getChannel()!!
        channel.observe(this, Observer<Resource<ChannelEntity>> { resource ->
            if (resource?.status == Status.SUCCESS) { // Fetched channel
                Timber.e("Fetched channel: " + resource.data?.description)
            }
        })

        // Load items for channel
        val items = viewModel.getItems()!!
        items.observe(this, Observer<Resource<List<ItemEntity>>> { resource ->
            if (resource?.status == Status.SUCCESS) { // Fetched items
                Timber.e("Fetch items: " + resource?.data?.size)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (inflater != null) {
            val rootView = inflater.inflate(R.layout.fragment_nba_rss, container, false)
            rootView
        } else {
            null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

private val RSS_TOPIC_PARAM_KEY = "param_topic"

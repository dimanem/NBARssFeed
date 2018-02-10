package com.dimanem.android.nba.rssreader.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimanem.android.nba.rssreader.R
import com.dimanem.android.nba.rssreader.api.NBARssService
import com.dimanem.android.nba.rssreader.di.Injectable
import com.dimanem.android.nba.rssreader.vo.RSS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dimanemets on 10/02/2018.
 */
class NBARssFragment : Fragment(), Injectable {

    var topic : String? = null

    var testTextView: TextView? = null

    @Inject
    lateinit var nbaRssService: NBARssService

    private lateinit var disposable: Disposable

    companion object {
        fun create(topic: String) : NBARssFragment {
            val rssFragment = NBARssFragment()
            val args = Bundle()
            args.putString(RSS_TOPIC_PARAM_KEY, topic)
            rssFragment.arguments = args
            return rssFragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args = arguments
        if (args != null && args.containsKey(RSS_TOPIC_PARAM_KEY)) {
            topic = args.getString(RSS_TOPIC_PARAM_KEY)
        }

        Timber.i("Topic is: " + topic)

        testApi()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (inflater != null) {
            val rootView = inflater.inflate(R.layout.fragment_nba_rss, container, false)
            testTextView = rootView.findViewById(R.id.testTextView)
            rootView
        } else {
            null
        }
    }

    override fun onDestroy() {
        if (disposable != null) {
            disposable.dispose()
        }
        super.onDestroy()
    }

    private fun testApi() {
        if (nbaRssService != null && topic != null) {
            disposable =
                    nbaRssService.getRssFeedNotEncoded(topic!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { result -> showResult(result) },
                                    { error -> showError(error.message) }
                            )
        } else {
            Timber.e("Can't test API!")
        }
    }

    private fun showError(message: String?) {
        testTextView!!.text = message
    }

    private fun showResult(result: Any) {
        if (result != null && testTextView != null) {
            val rss = result as RSS
            testTextView!!.text = rss.channel!!.title
        }
    }
}

private val RSS_TOPIC_PARAM_KEY = "param_topic"

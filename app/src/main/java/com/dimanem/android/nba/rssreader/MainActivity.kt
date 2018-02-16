package com.dimanem.android.nba.rssreader

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dimanem.android.nba.rssreader.db.ItemEntity
import com.dimanem.android.nba.rssreader.viewmodel.RssFeedViewModel
import com.dimanem.android.nba.rssreader.vo.Resource
import com.dimanem.android.nba.rssreader.vo.Status
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject
import android.R.attr.duration
import android.content.Context


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var rssViewModel: RssFeedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rssViewModel = ViewModelProviders.of(this, viewModelFactory).get(RssFeedViewModel::class.java)

        val context: Context = applicationContext

        // Observe items
        val items = rssViewModel!!.getItems()
        items?.observe(this, Observer<Resource<List<ItemEntity>>> { resource ->
            if (resource != null && resource.status == Status.SUCCESS) {
                val data = resource.data?.size
                Toast.makeText(context, "Loaded items: " + data, Toast.LENGTH_SHORT).show()
//                Timber.e("Fetch items: " + data)
            }
        })
    }
}

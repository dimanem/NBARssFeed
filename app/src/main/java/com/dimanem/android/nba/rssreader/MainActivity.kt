package com.dimanem.android.nba.rssreader

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dimanem.android.nba.rssreader.api.NBARssService
import com.dimanem.android.nba.rssreader.ui.NavigationController
import com.dimanem.android.nba.rssreader.vo.RSS
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var nbaRssService: NBARssService

    @Inject
    lateinit var navigationController: NavigationController

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testApi()
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun testApi() {
        disposable =
                nbaRssService!!.getAllFeeds()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult(result) },
                                { error -> showError(error.message) }
                        )
    }

    private fun showError(message: String?) {
        Timber.e(message)
    }

    private fun showResult(result: Any) {
        if (result != null) {
            val rss = result as RSS
            Timber.i("Version " + rss.version)
        }
    }
}

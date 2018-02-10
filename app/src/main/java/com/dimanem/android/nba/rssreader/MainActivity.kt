package com.dimanem.android.nba.rssreader

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dimanem.android.nba.rssreader.ui.NavigationController
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigationController: NavigationController

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController.navigateToRSS(RSS_TOPIC_NBA_ALL_NEWS)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}

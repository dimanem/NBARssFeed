package com.dimanem.android.nba.rssreader.di

import com.dimanem.android.nba.rssreader.ui.NBARssFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by dimanemets on 10/02/2018.
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun channelFragment(): NBARssFragment
}

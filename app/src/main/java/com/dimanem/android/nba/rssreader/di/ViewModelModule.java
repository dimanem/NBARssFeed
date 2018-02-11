package com.dimanem.android.nba.rssreader.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.dimanem.android.nba.rssreader.ui.rss.RssViewModel;
import com.dimanem.android.nba.rssreader.viewmodel.NBAViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RssViewModel.class)
    abstract ViewModel bindUserViewModel(RssViewModel rssViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(NBAViewModelFactory factory);
}

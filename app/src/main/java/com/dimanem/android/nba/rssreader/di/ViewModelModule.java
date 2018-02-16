package com.dimanem.android.nba.rssreader.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.dimanem.android.nba.rssreader.viewmodel.RssFeedViewModel;
import com.dimanem.android.nba.rssreader.viewmodel.NBARssViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RssFeedViewModel.class)
    abstract ViewModel bindUserViewModel(RssFeedViewModel rssViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(NBARssViewModelFactory factory);
}

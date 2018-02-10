package com.dimanem.android.nba.rssreader.di

import com.dimanem.android.nba.rssreader.AppExecutors
import com.dimanem.android.nba.rssreader.api.NBARssService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by dimanemets on 09/02/2018.
 */
@Module
class AppModule {

    @Singleton @Provides
    fun provideNBARssService(): NBARssService {
        // Debugging requests
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
                .baseUrl("https://www.nba.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create<NBARssService>(NBARssService::class.java!!)
    }

    @Singleton @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }
}

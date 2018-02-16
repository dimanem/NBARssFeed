package com.dimanem.android.nba.rssreader.di

import android.app.Application
import android.arch.persistence.room.Room
import com.dimanem.android.nba.rssreader.api.RssService
import com.dimanem.android.nba.rssreader.db.ItemDao
import com.dimanem.android.nba.rssreader.db.RssDB
import com.dimanem.android.nba.rssreader.util.LiveDataCallAdapterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by dimanemets on 09/02/2018.
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Singleton
    @Provides
    fun provideNBARssService(okHttpClient: OkHttpClient): RssService {
        return Retrofit.Builder()
                .baseUrl("https://www.nba.com/")
                .client(okHttpClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create<RssService>(RssService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(StethoInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideRssDb(app: Application): RssDB {
        return Room.databaseBuilder(app, RssDB::class.java, "nba_rss.db").build()
    }

    @Singleton
    @Provides
    fun provideItemDao(db: RssDB): ItemDao {
        return db.itemDao()
    }
}

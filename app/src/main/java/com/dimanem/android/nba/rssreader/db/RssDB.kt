package com.dimanem.android.nba.rssreader.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


/**
 * Created by dimanemets on 10/02/2018.
 */
@Database(entities = arrayOf(ChannelEntity::class, ItemEntity::class), version = 1)
abstract class RssDB : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun itemDao(): ItemDao
}

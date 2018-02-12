package com.dimanem.android.nba.rssreader.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


/**
 * Created by dimanemets on 11/02/2018.
 */
@Dao
interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(channel: ChannelEntity)

    @Query("SELECT * FROM channels")
    fun getAllChannels(): LiveData<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE id = :id")
    fun getChannelById(id: String): LiveData<ChannelEntity>
}

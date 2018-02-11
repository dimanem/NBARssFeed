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
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(items: List<ItemEntity>)

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE channelId = :channelId")
    fun getItemsForChannel(channelId: String): LiveData<List<ItemEntity>>
}

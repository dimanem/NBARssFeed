package com.dimanem.android.nba.rssreader.db

import android.arch.persistence.room.*
import com.dimanem.android.nba.rssreader.vo.Item

/**
 * Created by dimanemets on 11/02/2018.
 */
@Entity(tableName = "items"
        /*,foreignKeys = arrayOf(ForeignKey(entity = ChannelEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("channelId"),
                onDelete = ForeignKey.CASCADE))*/)
class ItemEntity() {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    // todo index
    var channelId: String? = null

    var title: String? = null
    var link: String? = null
    var description: String? = null
    var pubDate: String? = null

    @Ignore
    constructor(channelId: String, item: Item) : this() {
        this.channelId = channelId!!

        this.title = item.title
        this.link = item.link
        this.description = item.description
        this.pubDate = item.pubDate
    }
}

package com.dimanem.android.nba.rssreader.db

import android.arch.persistence.room.*
import com.dimanem.android.nba.rssreader.vo.Item

/**
 * Created by dimanemets on 11/02/2018.
 */
@Entity(tableName = "items")
class ItemEntity() {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var title: String? = null
    var link: String? = null
    var description: String? = null
    var pubDate: String? = null

    @Ignore
    constructor(item: Item) : this() {
        this.title = item.title
        this.link = item.link
        this.description = item.description
        this.pubDate = item.pubDate
    }
}

package com.dimanem.android.nba.rssreader.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.dimanem.android.nba.rssreader.vo.Channel

/**
 * Created by dimanemets on 11/02/2018.
 */
@Entity(tableName = "channels")
class ChannelEntity(){

    @PrimaryKey
    @NonNull
    var id: String = ""

    var title: String = ""
    var link: String? = ""
    var description: String? = ""
    var pubDate: String? = ""
    var imageUrl: String? = ""

    @Ignore
    constructor(channel: Channel): this() {
        // todo make id more robust
        id = channel.title!!

        title = channel.title!!
        link = channel.link
        description = channel.description
        pubDate = channel.pubDate
        imageUrl = channel.image?.url
    }
}

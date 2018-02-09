package com.dimanem.android.nba.rssreader.vo

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by dimanemets on 09/02/2018.
 */

@Root(name = "rss")
class RSS {

    @set:Attribute(name = "version")
    @get:Attribute(name = "version")
    var version: String? = null

    @set:Element (name = "channel")
    @get:Element (name = "channel")
    var channel: Channel? = null
}

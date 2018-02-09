package com.dimanem.android.nba.rssreader.vo

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by dimanemets on 09/02/2018.
 */
@Root(name = "item")
class Item {

    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String? = null

    @set:Element(name = "link")
    @get:Element(name = "link")
    var link: String? = null

    @set:Element(name = "description")
    @get:Element(name = "description")
    var description: String? = null

    @set:Element(name = "pubDate")
    @get:Element(name = "pubDate")
    var pubDate: String? = null
}

package com.dimanem.android.nba.rssreader.vo

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by dimanemets on 09/02/2018.
 */

@Root(name = "channel")
class Channel {

    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String? = null

    @set:Element(name = "link")
    @get:Element(name = "link")
    var link: String? = null

    @set:Element(name = "description")
    @get:Element(name = "description")
    var description: String? = null

    @set:Element(name = "language")
    @get:Element(name = "language")
    var language: String? = null

    @set:Element(name = "copyright")
    @get:Element(name = "copyright")
    var copyRight: String? = null

    @set:Element(name = "managingEditor")
    @get:Element(name = "managingEditor")
    var managingEditor: String? = null

    @set:Element(name = "pubDate")
    @get:Element(name = "pubDate")
    var pubDate: String? = null

    @set:Element(name = "lastBuildDate")
    @get:Element(name = "lastBuildDate")
    var lastBuildDate: String? = null

    @set:Element(name = "image")
    @get:Element(name = "image")
    var image: Image? = null

    @set:ElementList(name = "item", inline = true)
    @get:ElementList(name = "item", inline = true)
    var items: List<Item>? = null
}

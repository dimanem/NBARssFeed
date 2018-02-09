package com.dimanem.android.nba.rssreader.vo

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by dimanemets on 09/02/2018.
 */
@Root(name = "image")
class Image {

    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String? = null

    @set:Element(name = "link")
    @get:Element(name = "link")
    var link: String? = null

    @set:Element(name = "url")
    @get:Element(name = "url")
    var url: String? = null

    @set:Element(name = "width")
    @get:Element(name = "width")
    var width: Int? = null

    @set:Element(name = "height")
    @get:Element(name = "height")
    var height: Int? = null
}

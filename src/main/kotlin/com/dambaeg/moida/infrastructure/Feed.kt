package com.dambaeg.moida.infrastructure

import com.dambaeg.moida.domain.content.Post
import com.dambaeg.moida.domain.member.Member
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

object SyndFeed : Feed {
    var title: String = ""
    var description: String = ""

    override fun get(member: Member): Feed {
        val feed = SyndFeedInput().build(XmlReader(URL("${member.blogLink}/rss")))
        this.title = feed.entries[0].title
        this.description = feed.entries[0].description.value
        return this
    }

   override fun toPost(member: Member) = Post(member, title, description)
}

interface Feed {
    fun get(member: Member): Feed
    fun toPost(member: Member): Post
}
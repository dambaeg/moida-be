package com.dambaeg.moida.domain.content

import com.dambaeg.moida.domain.member.Member
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.net.URL

object Feed {
    fun get(member: Member): Post {
        val feed = SyndFeedInput().build(XmlReader(URL("${member.blogLink}/rss")))
        return Post(
                member,
                feed.entries[0].title,
                feed.entries[0].description.value)
    }
}
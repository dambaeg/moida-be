package com.dambaeg.moida.infrastructure

import com.dambaeg.moida.domain.member.Group
import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.domain.member.Party
import com.dambaeg.moida.infrastructure.SyndFeed
import org.assertj.core.api.Assertions
import org.junit.Test

class FeedTest {

    private val party = Party("백엔드", Group("글또"))

    @Test
    fun `🚀 Let's get rss feed information of url`() {
        val member = Member("bbd","https://brainbackdoor.tistory.com", party)
        val feed = SyndFeed.get(member)
        val post = feed.toPost(member)
        Assertions.assertThat(post.member).isEqualTo(member)
    }
}
package com.dambaeg.moida.infrastructure

import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.infrastructure.SyndFeed
import org.assertj.core.api.Assertions
import org.junit.Test

class FeedTest {

    @Test
    fun `🚀 Let's get rss feed information of url`() {
        val member = Member("bbd","https://brainbackdoor.tistory.com")
        val feed = SyndFeed.get(member)
        val post = feed.toPost(member)
        Assertions.assertThat(post.member).isEqualTo(member)
    }
}
package com.dambaeg.moida.domain.content

import com.dambaeg.moida.domain.member.Member
import org.assertj.core.api.Assertions
import org.junit.Test

class FeedTest {

    @Test
    fun `🚀 Let's get rss feed information of url`() {
        val member = Member("bbd","https://brainbackdoor.tistory.com")
        val feed = Feed.get(member)

        Assertions.assertThat(feed.member).isEqualTo(member)
    }
}
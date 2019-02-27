package com.dambaeg.moida.domain.content

import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.infrastructure.SyndFeed
import com.dambaeg.support.test.BaseTest
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

private val logger = KotlinLogging.logger { }

class PostTest : BaseTest() {

    private val BASE_URL = "https://brainbackdoor.tistory.com"

    @Test
    fun `피드가 게시글로 등록`() {
        val member = Member("bbd", BASE_URL)
        val feed = SyndFeed.get(member)
        val post = feed.toPost(member)
        logger.debug { post.title }
        softly.assertThat(post.description).isNotNull
    }

    @Test
    fun `게시글에 댓글 달기`() {
        val comment = "댓글"
        val member = Member("bbd", BASE_URL)
        val feed = SyndFeed.get(member)
        val post = feed.toPost(member)
        post.comment(Comment(comment))

        softly.assertThat(post.comments.first().content).isEqualTo(comment)
    }
}
package com.dambaeg.moida.domain.content

import com.dambaeg.moida.domain.member.Member
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

private val logger = KotlinLogging.logger { }

class PostTest {

    private val BASE_URL = "https://brainbackdoor.tistory.com"

    @Test
    fun `피드가 게시글로 등록`() {
        val post = Feed.get(Member("bbd", BASE_URL))

        logger.debug { post.title }
        assertThat(post.description).isNotNull()
    }
}
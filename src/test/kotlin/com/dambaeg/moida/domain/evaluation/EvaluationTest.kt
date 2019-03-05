package com.dambaeg.moida.domain.evaluation

import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.infrastructure.SyndFeed
import com.dambaeg.support.test.BaseTest
import org.junit.Test

class EvaluationTest : BaseTest() {
    private val BASE_URL = "https://brainbackdoor.tistory.com"
    @Test
    fun `항목별로 테스트`() {
        val member = Member("bbd", BASE_URL)
        val feed = SyndFeed.get(member)
        val post = feed.toPost(member)
        val evaluation = Evaluation(member, post, 1,3,2,4,5)

        softly.assertThat(evaluation.post.title).isEqualTo(post.title)
        softly.assertThat(evaluation.score1).isEqualTo(1)
    }
}
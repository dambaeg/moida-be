package com.dambaeg.moida.domain.member

import com.dambaeg.support.test.BaseTest
import org.junit.Test

class MemberTest : BaseTest() {

    private val BASE_URL = "https://brainbackdoor.tistory.com"
    @Test
    fun `파티, 그룹에 멤버 등록`() {
        val group = Group("글또")
        val party = Party("백엔드", group)
        val member = Member("bbd", BASE_URL, party)

        softly.assertThat(member.getGroupName()).isEqualTo(group.name)
    }
}
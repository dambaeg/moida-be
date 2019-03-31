package com.dambaeg.moida.domain.external

import com.dambaeg.support.test.BaseTest
import org.junit.Test

class SlackTest : BaseTest() {
    @Test
    fun `슬랙채널 ID 추출테스트`() {
        val slack = Slack("https://edu-connect.slack.com/messages/CH7MSCTH7/")

        softly.assertThat(slack.getChannelId()).isEqualTo("CH7MSCTH7")
    }
}
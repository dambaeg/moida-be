package com.dambaeg.moida.infrastructure

import com.dambaeg.moida.domain.external.Slack
import com.dambaeg.support.test.WebBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class SlackApiRepositoryTest : WebBaseTest() {

    @Autowired
    private lateinit var slackApiRepository: SlackApiRepository

    @Test
    fun `슬랙 메시지 보내기`() {
        val slack = Slack("https://edu-connect.slack.com/messages/CH7MSCTH7/")
        slackApiRepository.send(slack.getChannelId(), "test!!")
    }
}
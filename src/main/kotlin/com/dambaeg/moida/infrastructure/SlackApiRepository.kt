package com.dambaeg.moida.infrastructure

import com.dambaeg.moida.infrastructure.exception.ExternalApiException
import mu.KotlinLogging
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

private val logger = KotlinLogging.logger {  }

@Configuration
@ConfigurationProperties("slack.service")
class SlackProperties {
    lateinit var chatUrl: String

    lateinit var token: String

    val parameters: MultiValueMap<String, String> = LinkedMultiValueMap<String, String>()

    fun add(channel: String, text: String) {
        parameters.add("token", token)
        parameters.add("channel", channel)
        parameters.add("text", text)
    }
}

@Component
class SlackApiRepository {
    @Autowired
    lateinit var slackProperties: SlackProperties

    @Autowired
    lateinit var slackRest: SlackRest

    fun send(channel: String, text: String): ResponseEntity<String> {
        if(StringUtils.isBlank(channel)) {
            throw ExternalApiException("Slack 등록이 되지 않았습니다.")
        }

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        slackProperties.add(channel, text)
        return slackRest.request(HttpMethod.POST, slackProperties.chatUrl, slackProperties.parameters, headers)
    }
}

@Component
class SlackRest : Rest<MultiValueMap<String, String>> {
    override fun request(method: HttpMethod,
                         url: String,
                         contents: MultiValueMap<String, String>?,
                         headers: HttpHeaders): ResponseEntity<String> {
        logger.debug("request url : {}, params : {}", url, contents)
        return RestTemplate().exchange(
                url,
                method,
                HttpEntity(contents, headers),
                String::class.java
        )
    }
}
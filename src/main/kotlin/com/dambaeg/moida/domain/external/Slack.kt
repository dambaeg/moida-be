package com.dambaeg.moida.domain.external

import com.dambaeg.moida.domain.BaseEntity
import javax.persistence.*

const val CHANNEL_ID_INDEX_IN_SLACK_URL = 4

@Entity
class Slack(
        @Column(length = 150, unique = true)
        var url: String,

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
) : BaseEntity() {
    fun getChannelId() = url.split("/")[CHANNEL_ID_INDEX_IN_SLACK_URL]
}
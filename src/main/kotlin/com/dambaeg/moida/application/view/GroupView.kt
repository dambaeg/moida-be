package com.dambaeg.moida.application.view

import com.dambaeg.moida.domain.member.Group
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

const val GROUP_BASE_URL = "/api/groups"

@JsonIgnoreProperties(ignoreUnknown = true)
data class GroupView(val id: String, val name: String) {
    fun generateUrl(): String {
        return "$GROUP_BASE_URL/$id"
    }
}

data class GroupCreateView(val name: String)

fun toGroupView(group: Group) = GroupView(group.id, group.name)
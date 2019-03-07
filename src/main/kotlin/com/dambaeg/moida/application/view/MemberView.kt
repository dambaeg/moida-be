package com.dambaeg.moida.application.view

import com.dambaeg.moida.domain.member.Member
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

const val MEMBER_BASE_URL = "/api/members"

@JsonIgnoreProperties(ignoreUnknown = true)
data class MemberView(
        var id: String,
        var name: String,
        var blogLink: String,
        var posts: List<PostView>
) {
    fun generateUrl(): String {
        return "$MEMBER_BASE_URL/$id"
    }
}

fun toMemberView(member: Member): MemberView {
    return MemberView(
            member.id,
            member.name,
            member.blogLink,
            toPostsView(member.posts)
    )
}

data class MemberCreateView(
        val name: String,
        val partyName: String,
        val blogLink: String
)
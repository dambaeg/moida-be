package com.dambaeg.moida.domain.member

import com.dambaeg.moida.domain.BaseRandomIdEntity
import com.dambaeg.moida.domain.content.Post
import javax.persistence.*

@Entity
@Table(name = "TB_MEMBER")
data class Member(
        var name: String = "",
        var blogLink: String = "",

        @OneToOne
        var party: Party

) : BaseRandomIdEntity() {
    @OneToMany(mappedBy = "member", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    val posts: MutableList<Post> = mutableListOf()

    fun posting(post: Post): Post {
        this.posts.add(post)
        return posts.last()
    }

    fun getGroupName() = party.group.name
}
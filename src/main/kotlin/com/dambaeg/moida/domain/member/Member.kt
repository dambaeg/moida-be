package com.dambaeg.moida.domain.member

import com.dambaeg.moida.domain.BaseRandomIdEntity
import com.dambaeg.moida.domain.content.Post
import javax.persistence.*

@Entity
data class Member(
        val name: String,
        val blogLink: String
) : BaseRandomIdEntity() {
    @OneToMany(mappedBy = "member", cascade = [CascadeType.PERSIST], orphanRemoval = true)
    val posts: MutableList<Post> = mutableListOf()

    fun addPost(post: Post): Post {
        this.posts.add(post)
        return posts.last()
    }
}
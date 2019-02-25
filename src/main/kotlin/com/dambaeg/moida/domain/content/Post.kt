package com.dambaeg.moida.domain.content

import com.dambaeg.moida.domain.BaseRandomIdEntity
import com.dambaeg.moida.domain.member.Member
import javax.persistence.*

@Entity
class Post (
    @ManyToOne
    @JoinColumn(foreignKey = ForeignKey(name = "fk_post_to_member"))
    val member: Member,

    val title: String,

    @Column(columnDefinition = "TEXT")
    val description: String
) : BaseRandomIdEntity()
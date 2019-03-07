package com.dambaeg.moida.domain.evaluation

import com.dambaeg.moida.domain.BaseRandomIdEntity
import com.dambaeg.moida.domain.content.Post
import com.dambaeg.moida.domain.member.Member
import javax.persistence.*

@Entity
class Evaluation(
        @OneToOne
        val member: Member,

        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_evaluation_to_post"))
        val post: Post,

        val score1: Int = 0,
        val score2: Int = 0,
        val score3: Int = 0,
        val score4: Int = 0,
        val score5: Int = 0

) : BaseRandomIdEntity()
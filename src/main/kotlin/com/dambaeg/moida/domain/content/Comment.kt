package com.dambaeg.moida.domain.content

import com.dambaeg.moida.domain.BaseEntity
import javax.persistence.*

@Entity
class Comment(
        @Column(columnDefinition = "TEXT")
        val content: String,

        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_comment_to_post"))
        val post: Post = Post(),

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
) : BaseEntity()
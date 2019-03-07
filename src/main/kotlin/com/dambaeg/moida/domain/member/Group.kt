package com.dambaeg.moida.domain.member

import com.dambaeg.moida.domain.BaseRandomIdEntity
import javax.persistence.*


@Entity
@Table(name = "TB_GROUP")
data class Group(
        @Column(unique = true)
        var name: String = "",

        @OneToMany(mappedBy = "group", cascade = [CascadeType.PERSIST], orphanRemoval = true)
        var party: MutableList<Party> = mutableListOf()
) : BaseRandomIdEntity()
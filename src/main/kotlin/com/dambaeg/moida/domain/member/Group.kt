package com.dambaeg.moida.domain.member

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany


@Entity
class Group(
        var name: String = "",

        @OneToMany(mappedBy = "group", cascade = [CascadeType.PERSIST], orphanRemoval = true)
        var party: MutableList<Party> = mutableListOf()
)
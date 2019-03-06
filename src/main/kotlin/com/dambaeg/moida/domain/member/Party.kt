package com.dambaeg.moida.domain.member

import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Party(
        var name: String = "",

        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_party_to_group"))
        var group: Group = Group()
)
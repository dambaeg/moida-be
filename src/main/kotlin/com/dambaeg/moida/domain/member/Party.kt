package com.dambaeg.moida.domain.member

import com.dambaeg.moida.domain.BaseRandomIdEntity
import javax.persistence.*

@Entity
@Table(name = "TB_PARTY")
data class Party(
        var name: String = "",

        @ManyToOne
        @JoinColumn(foreignKey = ForeignKey(name = "fk_party_to_group"))
        var group: Group
) : BaseRandomIdEntity()
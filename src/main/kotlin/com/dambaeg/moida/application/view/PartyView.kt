package com.dambaeg.moida.application.view

import com.dambaeg.moida.domain.member.Party
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

const val PARTY_BASE_URL = "/api/parties"

@JsonIgnoreProperties(ignoreUnknown = true)
data class PartyView(val id: String, val name: String) {
    fun generateUrl(): String {
        return "$PARTY_BASE_URL/$id"
    }
}

data class PartyCreateView(val name: String, val groupName: String)

fun toPartyView(party: Party) = PartyView(party.id, party.name)
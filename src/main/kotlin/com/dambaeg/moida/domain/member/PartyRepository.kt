package com.dambaeg.moida.domain.member

import org.springframework.data.repository.CrudRepository

interface PartyRepository : CrudRepository<Party, String> {
    fun findByName(name: String) : Party
}
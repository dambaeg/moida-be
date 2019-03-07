package com.dambaeg.moida.domain.member

import org.springframework.data.repository.CrudRepository

interface GroupRepository : CrudRepository<Group, String> {
    fun findByName(name: String): Group
}
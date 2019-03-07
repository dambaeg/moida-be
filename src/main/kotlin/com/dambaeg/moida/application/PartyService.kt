package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.PartyCreateView
import com.dambaeg.moida.application.view.toPartyView
import com.dambaeg.moida.domain.member.Group
import com.dambaeg.moida.domain.member.Party
import com.dambaeg.moida.domain.member.PartyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class PartyService @Autowired constructor(
        private val partyRepository: PartyRepository,
        private val groupService: GroupService
) {
    private fun save(name: String, group: Group) = partyRepository.save(Party(name, group))

    private fun findGroup(view: PartyCreateView) = groupService.find(view.groupName)

    fun create(view: PartyCreateView) = toPartyView(save(view.name, findGroup(view)))

    fun find(name: String) = partyRepository.findByName(name)

    fun findViewById(id: String) = toPartyView(partyRepository.findById(id).get())
}
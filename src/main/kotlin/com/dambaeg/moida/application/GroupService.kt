package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.GroupCreateView
import com.dambaeg.moida.application.view.toGroupView
import com.dambaeg.moida.domain.member.Group
import com.dambaeg.moida.domain.member.GroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class GroupService @Autowired constructor(
        private val groupRepository: GroupRepository
) {
    private fun save(name: String) = groupRepository.save(Group(name))

    fun find(name: String)  = groupRepository.findByName(name)

    fun create(view: GroupCreateView)= toGroupView(save(view.name))

    fun findViewById(id: String) = toGroupView(groupRepository.findById(id).get())
}
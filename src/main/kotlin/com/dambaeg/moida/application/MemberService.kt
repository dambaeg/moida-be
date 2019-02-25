package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.MemberCreateView
import com.dambaeg.moida.application.view.MemberView
import com.dambaeg.moida.application.view.toMemberView
import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.domain.member.MemberRepository
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberService @Autowired constructor(
        private val memberRepository: MemberRepository
) {

    fun createMember(member: MemberCreateView): MemberView {
        val member = Member(member.name, member.blogLink)
        val persistMember = memberRepository.save(member)
        return toMemberView(persistMember)
    }

    fun findById(memberId: String): Member {
        return memberRepository.findById(memberId).orElseThrow { NotFoundException("등록된 유저가 없습니다.") }
    }

    fun findViewById(memberid: String): MemberView {
        return toMemberView(findById(memberid))
    }
}
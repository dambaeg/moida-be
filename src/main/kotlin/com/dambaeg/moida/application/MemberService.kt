package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.*
import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.domain.member.MemberRepository
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class MemberService @Autowired constructor(
        private val memberRepository: MemberRepository,
        private val partyService: PartyService
) {
    @Autowired
    private lateinit var postService: PostService

    fun createMember(view: MemberCreateView): MemberView {
        val party = partyService.find(view.partyName)
        val member = Member(view.name, view.blogLink, party)
        val persistMember = memberRepository.save(member)
        return toMemberView(persistMember)
    }

    fun findById(memberId: String) =
            memberRepository.findById(memberId).orElseThrow { NotFoundException("등록된 유저가 없습니다.") }!!

    fun findViewById(memberId: String) = toMemberView(findById(memberId))

    fun posting(memberId: String): PostView {
        val persistMember = findById(memberId)
        val post = postService.retrieve(persistMember)

        return toPostView(persistMember.posting(post))
    }
}
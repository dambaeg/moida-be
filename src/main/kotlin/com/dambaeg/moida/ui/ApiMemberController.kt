package com.dambaeg.moida.ui

import com.dambaeg.moida.application.MemberService
import com.dambaeg.moida.application.PostService
import com.dambaeg.moida.application.view.MEMBER_BASE_URL
import com.dambaeg.moida.application.view.MemberCreateView
import com.dambaeg.moida.application.view.MemberView
import com.dambaeg.moida.application.view.PostView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(MEMBER_BASE_URL)
class ApiMemberController {

    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var postService: PostService

    @PostMapping("")
    fun create(@RequestBody member: MemberCreateView): ResponseEntity<MemberView> {
        val memberView = memberService.createMember(member)
        return ResponseEntity.created(URI(memberView.generateUrl())).body(memberView)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<MemberView> {
        val member = memberService.findViewById(id)
        return ResponseEntity.ok(member)
    }

    @PostMapping("/{id}/post")
    fun addPost(@PathVariable id: String): ResponseEntity<PostView> {
        val latestPostView = postService.addPost(id)
        return ResponseEntity.created(URI(latestPostView.generateUrl())).body(latestPostView)
    }
}
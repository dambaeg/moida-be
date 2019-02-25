package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.PostView
import com.dambaeg.moida.application.view.toPostView
import com.dambaeg.moida.application.view.toPostsView
import com.dambaeg.moida.domain.content.Feed
import com.dambaeg.moida.domain.content.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class PostService @Autowired constructor(
        private val postRepository: PostRepository,
        private val memberService: MemberService
) {
    fun addPost(memberId: String): PostView {
        val persistMember = memberService.findById(memberId)
        val latestPost = persistMember.addPost(Feed.get(persistMember))
        return toPostView(latestPost)
    }

    fun findPosts() = toPostsView(postRepository.findAll())

    fun findPost(id: String) = toPostView(postRepository.findById(id).get())
}
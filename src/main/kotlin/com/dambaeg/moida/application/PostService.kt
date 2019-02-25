package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.toPostView
import com.dambaeg.moida.application.view.toPostsView
import com.dambaeg.moida.domain.content.PostRepository
import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.infrastructure.SyndFeed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class PostService @Autowired constructor(
        private val postRepository: PostRepository
) {
    fun retrieveFeed(member: Member) = SyndFeed.get(member)

    fun retrieve(member: Member) = retrieveFeed(member).toPost(member)

    fun findPosts() = toPostsView(postRepository.findAll())

    fun findPost(id: String) = toPostView(postRepository.findById(id).get())
}
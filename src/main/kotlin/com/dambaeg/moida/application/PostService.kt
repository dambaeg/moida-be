package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.toCommentsView
import com.dambaeg.moida.application.view.toPostView
import com.dambaeg.moida.application.view.toPostsView
import com.dambaeg.moida.domain.content.Comment
import com.dambaeg.moida.domain.content.Post
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

    fun retrieve(member: Member): Post {
        val post = retrieveFeed(member).toPost(member)
        return postRepository.save(post)
    }

    fun findPosts() = toPostsView(postRepository.findAll())

    fun findPost(id: String) = toPostView(findById(id))

    private fun findById(id: String) = postRepository.findById(id).get()

    fun addComment(id: String, comment: String) {
        val post = findById(id)
        post.comment(Comment(comment))
    }

    fun findComments(id: String) = toCommentsView(findById(id).comments)
}
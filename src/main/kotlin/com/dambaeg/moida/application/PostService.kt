package com.dambaeg.moida.application

import com.dambaeg.moida.application.view.*
import com.dambaeg.moida.domain.content.Post
import com.dambaeg.moida.domain.content.PostRepository
import com.dambaeg.moida.domain.evaluation.Evaluation
import com.dambaeg.moida.domain.evaluation.EvaluationRepository
import com.dambaeg.moida.domain.member.Member
import com.dambaeg.moida.infrastructure.SlackApiRepository
import com.dambaeg.moida.infrastructure.SyndFeed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class PostService @Autowired constructor(
        private val postRepository: PostRepository,
        private val evaluationRepository: EvaluationRepository,
        private val slackApiRepository: SlackApiRepository,
        private val memberService: MemberService
) {
    fun retrieveFeed(member: Member) = SyndFeed.get(member)

    fun retrieve(member: Member): Post {
        val post = retrieveFeed(member).toPost(member)
        slackApiRepository.send("CH7MSCTH7", "<${post.uri}>")
        return postRepository.save(post)
    }

    fun findPosts() = toPostsView(postRepository.findAll())

    fun findPost(id: String) = toPostView(findById(id))

    private fun findById(id: String) = postRepository.findById(id).get()

    fun addComment(id: String, content: String) {
        val post = findById(id)
        post.comment(content)
    }

    fun findComments(id: String) = toCommentsView(findById(id).comments)

    fun evaluate(id: String, view: EvaluationCreateView) {
        val post = postRepository.findById(id).get()
        val member = memberService.findById(view.memberId)

        val evaluation = Evaluation(member, post, view.score1, view.score2, view.score3, view.score4, view.score5)
        evaluationRepository.save(evaluation)
    }

    fun findEvaluationsById(id: String) = toEvaluationsView(evaluationRepository.findByPostId(id))
}
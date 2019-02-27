package com.dambaeg.moida.ui

import com.dambaeg.moida.application.PostService
import com.dambaeg.moida.application.view.CommentView
import com.dambaeg.moida.application.view.POST_BASE_URL
import com.dambaeg.moida.application.view.PostView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(POST_BASE_URL)
class ApiPostController {
    @Autowired
    lateinit var postService: PostService

    @GetMapping
    fun findPosts(): ResponseEntity<List<PostView>> {
        val posts = postService.findPosts()
        return ResponseEntity.ok(posts)
    }

    @GetMapping("/{id}")
    fun findPost(@PathVariable id: String): ResponseEntity<PostView> {
        val post = postService.findPost(id)
        return ResponseEntity.ok(post)
    }

    @PostMapping("/{id}/comments")
    fun addComment(@PathVariable id: String, @RequestBody content: String): ResponseEntity<Unit> {
        postService.addComment(id, content)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}/comments")
    fun findComments(@PathVariable id: String) : ResponseEntity<List<CommentView>> {
        val comments = postService.findComments(id)
        return ResponseEntity.ok(comments)
    }
}
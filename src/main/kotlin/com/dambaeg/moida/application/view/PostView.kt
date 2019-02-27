package com.dambaeg.moida.application.view

import com.dambaeg.moida.domain.content.Comment
import com.dambaeg.moida.domain.content.Post
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

const val POST_BASE_URL = "/api/posts"

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostView(
        var id: String,
        var title: String,
        var description: String,
        var memberName: String
){
    fun generateUrl(): String {
        return "$POST_BASE_URL/$id"
    }
}

fun toPostView(post: Post) =
        PostView(
                post.id,
                post.title,
                post.description,
                post.member.name)

fun toPostsView(posts: List<Post>) = posts.map { toPostView(it) }

data class CommentView(var content: String)
fun toCommentView(comment: Comment) = CommentView(comment.content)
fun toCommentsView(comments: MutableList<Comment>) = comments.map { toCommentView(it) }
package com.dambaeg.moida.ui

import com.dambaeg.moida.application.view.*
import com.dambaeg.support.test.WebBaseTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.mapper.TypeRef
import io.restassured.specification.RequestSpecification
import org.junit.Test
import org.springframework.http.HttpStatus

class MemberAcceptanceTest : WebBaseTest() {

    private val BASE_URL = "https://brainbackdoor.tistory.com"
    val memberView = MemberCreateView("bbd", BASE_URL)

    @Test
    fun `멤버 등록`() {
        val memberView = givenAnonymous().with()
                .body(memberView)
                .post(MEMBER_BASE_URL)
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(MemberView::class.java)

        softly.assertThat(memberView.name).isEqualTo(this.memberView.name)
    }

    @Test
    fun `피드가 게시글로 등록`() {
        // 회원을 등록한다.
        val memberView = givenAnonymous().with()
                .body(memberView)
                .post("$MEMBER_BASE_URL")
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(MemberView::class.java)

        // 포스팅을 가져온다.
        val latestPostView = givenAnonymous().with()
                .post(memberView.generateUrl() + "/posting")
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(PostView::class.java)

        softly.assertThat(latestPostView.memberName).isEqualTo(memberView.name)
    }

    @Test
    fun `등록된 게시글에 댓글 달기`() {
        val content = "댓글"
        // 회원을 등록한다.
        val memberView = givenAnonymous().with()
                .body(memberView)
                .post(MEMBER_BASE_URL)
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(MemberView::class.java)

        // 포스팅을 가져온다.
        val latestPostView = givenAnonymous().with()
                .post(memberView.generateUrl() + "/posting")
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(PostView::class.java)

        // 댓글을 등록한다.
        givenAnonymous().with()
                .body(content)
                .post(latestPostView.generateUrl() + "/comments")

        // 댓글을 가져온다
        val commentsView = givenAnonymous().with()
                .get(latestPostView.generateUrl() + "/comments")
                .then().statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<CommentView>>() {})
        softly.assertThat(commentsView.first().content).isEqualTo(content)
    }

    @Test
    fun `등록된 게시글 평가하기`() {
        // 회원을 등록한다.
        val memberView = givenAnonymous().with()
                .body(memberView)
                .post(MEMBER_BASE_URL)
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(MemberView::class.java)

        // 포스팅을 가져온다.
        val latestPostView = givenAnonymous().with()
                .post(memberView.generateUrl() + "/posting")
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(PostView::class.java)

        // 평가한다.
        val view = EvaluationCreateView(memberView.id, 3, 4, 5, 1, 2)
        givenAnonymous().with()
                .body(view)
                .post(latestPostView.generateUrl() + "/evaluations")

        // 평가 결과를 가져온다.
        val resultsView = givenAnonymous().with()
                .get(latestPostView.generateUrl() + "/evaluations")
                .then().statusCode(HttpStatus.OK.value())
                .extract()
                .`as`(object : TypeRef<List<EvaluationView>>() {})
        softly.assertThat(resultsView.first().score1).isEqualTo(view.score1)
    }
}

fun givenAnonymous(): RequestSpecification {
    return RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .log().all()
}

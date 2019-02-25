package com.dambaeg.moida.ui

import com.dambaeg.moida.application.view.MEMBER_BASE_URL
import com.dambaeg.moida.application.view.MemberCreateView
import com.dambaeg.moida.application.view.MemberView
import com.dambaeg.moida.application.view.PostView
import com.dambaeg.support.test.WebBaseTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
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
                .post("$MEMBER_BASE_URL")
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
                .post(memberView.generateUrl() + "/post")
                .then().statusCode(HttpStatus.CREATED.value())
                .extract()
                .`as`(PostView::class.java)

        softly.assertThat(latestPostView.memberName).isEqualTo(memberView.name)
    }
}

fun givenAnonymous(): RequestSpecification {
    return RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .log().all()
}

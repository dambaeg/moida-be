package com.dambaeg.support.test

import io.restassured.RestAssured
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
abstract class WebBaseTest: BaseTest(){
    @Value("\${local.server.port}")
    private var serverPort: Int = 0

    @Before
    fun setup() {
        RestAssured.port = serverPort
    }
}
package com.dambaeg.support.test

import org.assertj.core.api.JUnitSoftAssertions
import org.junit.Rule
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
open class BaseTest {
    @get:Rule
    var softly = JUnitSoftAssertions()
}
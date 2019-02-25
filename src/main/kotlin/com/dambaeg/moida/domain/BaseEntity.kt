package com.dambaeg.moida.domain

import org.apache.commons.lang3.RandomStringUtils
import org.hibernate.annotations.GenericGenerator
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity {
    @CreatedDate
    var createdDate: LocalDateTime? = null

    @LastModifiedDate
    var updatedDate: LocalDateTime? = null
}

@MappedSuperclass
class BaseRandomIdEntity(
        @Id
        @GenericGenerator(name = "random_id", strategy = "com.dambaeg.moida.domain.RandomValueIdGenerator")
        @GeneratedValue(generator = "random_id")
        @Column(length = RandomValueIdGenerator.ID_LENGTH, nullable = false)
        val id: String = ""
): BaseEntity()

class RandomValueIdGenerator : IdentifierGenerator {
    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): Serializable {
        return RandomStringUtils.randomAlphanumeric(ID_LENGTH)
    }

    companion object {
        const val ID_LENGTH = 8
    }
}
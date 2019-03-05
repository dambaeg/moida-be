package com.dambaeg.moida.domain.evaluation

import org.springframework.data.repository.CrudRepository

interface EvaluationRepository : CrudRepository<Evaluation, String> {
    fun findByPostId(postId: String): List<Evaluation>
}
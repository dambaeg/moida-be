package com.dambaeg.moida.application.view

import com.dambaeg.moida.domain.evaluation.Evaluation

data class EvaluationCreateView(
        val memberId: String = "",
        val score1: Int = 0,
        val score2: Int = 0,
        val score3: Int = 0,
        val score4: Int = 0,
        val score5: Int = 0
)

data class EvaluationView(
        val memberName: String = "",
        val score1: Int = 0,
        val score2: Int = 0,
        val score3: Int = 0,
        val score4: Int = 0,
        val score5: Int = 0
)

fun toEvaluationsView(evaluations: List<Evaluation>) = evaluations.map { toEvaluationView(it) }

fun toEvaluationView(evaluation: Evaluation) =
        EvaluationView(
                evaluation.member.name,
                evaluation.score1,
                evaluation.score2,
                evaluation.score3,
                evaluation.score4,
                evaluation.score5
        )


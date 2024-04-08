package com.nealarchival.quicktrivia.model

class QuizResults(
    val correct: Int,
    val incorrect: Int
) {
    val total: Int
        get() = correct + incorrect

    val score: Int
        get()  {
            val percentage = correct.toDouble() / total.toDouble() * 100.0
            return percentage.toInt()
        }
}

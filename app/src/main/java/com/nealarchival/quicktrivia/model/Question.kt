package com.nealarchival.quicktrivia.model

enum class QuestionType {
    BOOLEAN,
    MULTIPLE
}

public class Question(
    val text: String = "",
    val correctAnswer: String = "",
    val incorrectAnswers: Array<String> = arrayOf(),
    val type: QuestionType = QuestionType.MULTIPLE,
) {
    val allAnswers: Array<String>
        get() {
            if (type == QuestionType.BOOLEAN) {
                return arrayOf("True", "False")
            }

            val multipleAnswers = incorrectAnswers.plus(element = correctAnswer)
            multipleAnswers.shuffle()

            return multipleAnswers
        }
}

public class QuestionMockData {
    val question0 = Question(
        text = "Which planet is known as the Red Planet?",
        correctAnswer = "Mars",
        incorrectAnswers = arrayOf("Jupiter", "Venus", "Earth"),
        type = QuestionType.MULTIPLE
    )

    val question1 = Question(
        text = "Who is the author of 'To Kill a Mockingbird'?",
        correctAnswer = "Harper Lee",
        incorrectAnswers = arrayOf("Ernest Hemingway", "F. Scott Fitzgerald", "Mark Twain"),
        type = QuestionType.MULTIPLE
    )

    val question2 = Question(
        text = "What is the chemical symbol for water?",
        correctAnswer = "H2O",
        incorrectAnswers = arrayOf("CO2", "O2", "NaCl"),
        type = QuestionType.MULTIPLE
    )

    val question3 = Question(
        text = "Is the Atlantic Ocean the largest ocean on Earth?",
        correctAnswer = "No",
        type = QuestionType.BOOLEAN
    )

    val question4 = Question(
        text = "Was Albert Einstein born in the 19th century?",
        correctAnswer = "Yes",
        type = QuestionType.BOOLEAN
    )

    fun allQuestions(): Array<Question> {
        return arrayOf(question0, question1, question2, question3, question4)
    }
}
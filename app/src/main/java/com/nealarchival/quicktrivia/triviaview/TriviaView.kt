package com.nealarchival.quicktrivia.triviaview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.nealarchival.quicktrivia.model.Question
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nealarchival.quicktrivia.model.QuestionMockData

class TriviaViewModel(questions: Array<Question>, showResults: (Int, Int) -> Unit) : ViewModel() {
    private val _questions: MutableState<Array<Question>> = mutableStateOf(questions)
    private val showResults: (Int, Int) -> Unit = showResults

    private val _currentIndex: MutableState<Int> = mutableIntStateOf(0)
    private val _correctCount: MutableState<Int> = mutableIntStateOf(value = 0)
    private val _incorrectCount: MutableState<Int> = mutableIntStateOf(value = 0)

    val currentQuestion: Question?
        get() = _questions.value.getOrNull(index = _currentIndex.value)

    fun onAnswerClick(isCorrect: Boolean) {
        if (isCorrect) {
            _correctCount.value += 1
        } else {
            _incorrectCount.value += 1
        }

        if (_currentIndex.value < _questions.value.size - 1) {
            _currentIndex.value += 1
        } else { // End of the quiz.
            showResults(_correctCount.value, _incorrectCount.value)
        }
    }
}

@Composable
fun TriviaView(viewModel: TriviaViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        val nullableQuestion = viewModel.currentQuestion
        nullableQuestion?.let { question ->
            QuestionView(question = question, onAnswerClick = viewModel::onAnswerClick)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun TriviaPreview() {
    val questions = QuestionMockData().allQuestions()
    fun printResults(correct: Int, incorrect: Int) {
        println("Correct: $correct")
        println("Correct: $incorrect")
    }

    val triviaViewModel = TriviaViewModel(questions = questions, showResults = ::printResults)
    TriviaView(viewModel = triviaViewModel)
}

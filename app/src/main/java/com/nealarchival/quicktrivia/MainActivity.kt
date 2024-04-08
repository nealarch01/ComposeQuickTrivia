package com.nealarchival.quicktrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.nealarchival.quicktrivia.homeview.HomeView
import com.nealarchival.quicktrivia.model.Question
import com.nealarchival.quicktrivia.model.QuestionMockData
import com.nealarchival.quicktrivia.model.QuizResults
import com.nealarchival.quicktrivia.resultsview.ResultsView
import com.nealarchival.quicktrivia.triviaview.TriviaView
import com.nealarchival.quicktrivia.triviaview.TriviaViewModel
import com.nealarchival.quicktrivia.ui.theme.QuickTriviaTheme

enum class AppViews {
    HOME,
    TRIVIA,
    RESULTS
}

class RootViewModel : ViewModel() {
    private val _currentView: MutableState<AppViews> = mutableStateOf(AppViews.HOME)
    val currentView: AppViews
        get() = _currentView.value

    private val _questions: MutableState<Array<Question>> = mutableStateOf(arrayOf())
    val questions: Array<Question>
        get() = _questions.value

    private val _quizResults: MutableState<QuizResults> = mutableStateOf(QuizResults(correct = 0, incorrect = 0))
    val quizResults: QuizResults
        get() = _quizResults.value

    private val _showError: MutableState<Boolean> = mutableStateOf(false)
    private val _errorMessage: MutableState<String> = mutableStateOf("")

    private fun fetchTriviaQuizQuestions(): Array<Question> {
        // Simulate fetching trivia data.
        val sampleQuestions = QuestionMockData().allQuestions()
        sampleQuestions.shuffle()
        return sampleQuestions
    }

    fun startTriviaQuiz() {
        // Simulate fetch.
        val triviaQuizQuestions = fetchTriviaQuizQuestions()

        if (triviaQuizQuestions.isEmpty()) {
            _showError.value = true
            _errorMessage.value = "Error Fetching Trivia Quiz Questions"
            return
        }

        // Fetch was successful.
        _questions.value = triviaQuizQuestions
        _currentView.value = AppViews.TRIVIA
    }

    fun showResults(correct: Int, incorrect: Int) {
        _quizResults.value = QuizResults(correct = correct, incorrect = incorrect)
        _currentView.value = AppViews.RESULTS
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickTriviaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootView(viewModel = RootViewModel())
                }
            }
        }
    }
}

@Composable
fun RootView(viewModel: RootViewModel) {
    when (viewModel.currentView) {
        AppViews.HOME ->
            return HomeView(startButtonAction = viewModel::startTriviaQuiz)
        AppViews.TRIVIA ->
            return TriviaView(viewModel = TriviaViewModel(
                questions = viewModel.questions,
                showResults = viewModel::showResults)
            )
        AppViews.RESULTS ->
            return ResultsView(quizResults = viewModel.quizResults)
    }
}

@Preview(showBackground = true)
@Composable
fun RootViewPreview() {
    RootView(viewModel = RootViewModel())
}
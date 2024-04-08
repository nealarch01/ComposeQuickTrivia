package com.nealarchival.quicktrivia.resultsview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nealarchival.quicktrivia.model.QuizResults

@Composable
fun ResultsView(quizResults: QuizResults) {
    val quizResults = quizResults

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Text("Score: ${quizResults.score}%", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text("You answered ${quizResults.correct} / ${quizResults.total} questions correctly.", fontSize = 14.sp)
    }
}

@Preview
@Composable
fun ResultsViewPreview() {
    ResultsView(QuizResults(correct = 8, incorrect = 2))
}

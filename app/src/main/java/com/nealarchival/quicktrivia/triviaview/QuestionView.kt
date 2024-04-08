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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nealarchival.quicktrivia.model.Question
import com.nealarchival.quicktrivia.model.QuestionMockData

@Composable
fun QuestionView(question: Question, onAnswerClick: (Boolean) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            question.text, fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        for (answer in question.allAnswers) {
            Button(
                onClick = {
                    onAnswerClick(answer == question.correctAnswer)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(answer)
            }
        }
    }
}

@Preview
@Composable
fun QuestionViewPreview() {
    val questionMockData = QuestionMockData()
    val sampleQuestion = questionMockData.question0

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        QuestionView(question = sampleQuestion, onAnswerClick = {})
    }
}

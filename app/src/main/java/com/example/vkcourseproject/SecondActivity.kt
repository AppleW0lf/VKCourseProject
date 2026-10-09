package com.example.vkcourseproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkcourseproject.ui.theme.VKCourseProjectTheme

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent.getStringExtra(EXTRA_TEXT)

        setContent {
            VKCourseProjectTheme {
                Box(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (text.isNullOrBlank()) "Текст не передан" else text,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }
}
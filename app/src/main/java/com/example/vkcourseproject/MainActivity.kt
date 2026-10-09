package com.example.vkcourseproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkcourseproject.ui.theme.VKCourseProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKCourseProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IntentsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IntentsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                isError = false
            },
            label = { Text("Введите текст") },
            isError = isError,
            supportingText = {
                if (isError) Text("Поле не должно быть пустым")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                val value = text.trim()
                if (value.isEmpty()) {
                    isError = true
                    return@Button
                }
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.EXTRA_TEXT, value)
                }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            onClick = {
                val number = text.trim()
                if (number.isEmpty() || !number.matches(Regex("^[+]?\\d{5,15}$"))) {
                    Toast.makeText(context, "Введите корректный номер", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$number")
                }
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Нет приложения для звонков", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = {
                val value = text.trim()
                if (value.isEmpty()) {
                    Toast.makeText(context, "Нечего отправлять", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, value)
                }
                val chooser = Intent.createChooser(intent, "Поделиться через…")
                try {
                    context.startActivity(chooser)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Нет приложений для отправки", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntentsScreenPreview() {
    VKCourseProjectTheme {
        IntentsScreen()
    }
}
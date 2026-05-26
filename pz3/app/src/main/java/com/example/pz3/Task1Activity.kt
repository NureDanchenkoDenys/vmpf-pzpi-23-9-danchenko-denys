package com.example.pz3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Task1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Завдання 1",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {
                                Toast.makeText(
                                    this@Task1Activity,
                                    "Вітаємо з першим додатком на Kotlin!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Показати повідомлення")
                        }
                    }
                }
            }
        }
    }
}
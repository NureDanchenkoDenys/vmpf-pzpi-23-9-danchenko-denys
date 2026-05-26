package com.example.pz3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Task2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TodoScreen()
            }
        }
    }
}

data class Task(
    val text: String,
    val completed: Boolean = false
)

@Composable
fun TodoScreen() {

    var taskText by remember {
        mutableStateOf("")
    }

    val tasks = remember {
        mutableStateListOf<Task>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "ToDo список",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = taskText,
            onValueChange = {
                taskText = it
            },
            label = {
                Text("Введіть завдання")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                if (taskText.isNotBlank()) {

                    tasks.add(Task(taskText))
                    taskText = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Додати")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            itemsIndexed(tasks) { index, task ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(task.text)

                    Checkbox(
                        checked = task.completed,

                        onCheckedChange = {
                            tasks[index] =
                                task.copy(completed = it)
                        }
                    )
                }
            }
        }
    }
}
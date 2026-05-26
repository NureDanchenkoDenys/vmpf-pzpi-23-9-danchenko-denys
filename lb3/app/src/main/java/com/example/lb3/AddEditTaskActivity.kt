package com.example.lb3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditTaskActivity : AppCompatActivity() {

    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit_task)

        val screenTitleTextView = findViewById<TextView>(R.id.screenTitleTextView)
        val taskTitleEditText = findViewById<EditText>(R.id.taskTitleEditText)
        val taskDescriptionEditText = findViewById<EditText>(R.id.taskDescriptionEditText)
        val saveTaskButton = findViewById<Button>(R.id.saveTaskButton)
        val backButton = findViewById<Button>(R.id.backButton)

        taskId = intent.getIntExtra("taskId", -1)

        if (taskId != -1) {
            screenTitleTextView.text = "Редагування задачі"
            saveTaskButton.text = "Зберегти зміни"

            val task = TaskRepository.getTaskById(taskId)

            taskTitleEditText.setText(task?.title)
            taskDescriptionEditText.setText(task?.description)
        }

        saveTaskButton.setOnClickListener {
            val title = taskTitleEditText.text.toString().trim()
            val description = taskDescriptionEditText.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (taskId == -1) {
                TaskRepository.addTask(title, description)
                Toast.makeText(this, "Задачу додано", Toast.LENGTH_SHORT).show()
            } else {
                TaskRepository.updateTask(taskId, title, description)
                Toast.makeText(this, "Задачу оновлено", Toast.LENGTH_SHORT).show()
            }

            finish()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
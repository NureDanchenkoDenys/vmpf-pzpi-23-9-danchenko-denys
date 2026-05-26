package com.example.lb3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_task_details)

        val detailsTitleTextView = findViewById<TextView>(R.id.detailsTitleTextView)
        val detailsDescriptionTextView = findViewById<TextView>(R.id.detailsDescriptionTextView)
        val detailsStatusTextView = findViewById<TextView>(R.id.detailsStatusTextView)
        val backButton = findViewById<Button>(R.id.backButton)

        val taskId = intent.getIntExtra("taskId", -1)
        val task = TaskRepository.getTaskById(taskId)

        if (task != null) {
            detailsTitleTextView.text = task.title
            detailsDescriptionTextView.text = task.description

            detailsStatusTextView.text =
                if (task.isCompleted) {
                    "Статус: виконано"
                } else {
                    "Статус: не виконано"
                }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
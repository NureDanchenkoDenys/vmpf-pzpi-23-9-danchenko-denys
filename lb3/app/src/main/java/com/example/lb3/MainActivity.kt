package com.example.lb3

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tasksContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        tasksContainer = findViewById(R.id.tasksContainer)

        val addTaskButton = findViewById<Button>(R.id.addTaskButton)

        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddEditTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        showTasks()
    }

    private fun showTasks() {
        tasksContainer.removeAllViews()

        val tasks = TaskRepository.getTasks()

        if (tasks.isEmpty()) {
            val emptyTextView = TextView(this).apply {
                text = "Поки що задач немає"
                textSize = 18f
                setTextColor(Color.parseColor("#6B7280"))
                gravity = Gravity.CENTER
                setPadding(0, 30, 0, 0)
            }

            tasksContainer.addView(emptyTextView)
            return
        }

        for (task in tasks) {
            addTaskView(task)
        }
    }

    private fun addTaskView(task: Task) {
        val taskLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.WHITE)
            elevation = 5f
        }

        val infoLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val checkBox = CheckBox(this).apply {
            isChecked = task.isCompleted
        }

        val taskTextView = TextView(this).apply {
            text = "${task.title}\n${task.description}"
            textSize = 16f
            setTextColor(Color.parseColor("#1F2937"))
            setPadding(8, 0, 8, 0)

            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )

            if (task.isCompleted) {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            TaskRepository.changeTaskStatus(task.id, isChecked)

            taskTextView.paintFlags =
                if (isChecked) {
                    taskTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    taskTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
        }

        taskLayout.setOnClickListener {
            val intent = Intent(this, TaskDetailsActivity::class.java)
            intent.putExtra("taskId", task.id)
            startActivity(intent)
        }

        val buttonsLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(0, 18, 0, 0)
        }

        val editButton = Button(this).apply {
            text = "Редагувати"
            isAllCaps = false

            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            ).apply {
                marginEnd = 10
            }
        }

        val deleteButton = Button(this).apply {
            text = "Видалити"
            isAllCaps = false

            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        editButton.setOnClickListener {
            val intent = Intent(this, AddEditTaskActivity::class.java)
            intent.putExtra("taskId", task.id)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            TaskRepository.deleteTask(task.id)
            showTasks()
        }

        infoLayout.addView(checkBox)
        infoLayout.addView(taskTextView)

        buttonsLayout.addView(editButton)
        buttonsLayout.addView(deleteButton)

        taskLayout.addView(infoLayout)
        taskLayout.addView(buttonsLayout)

        val marginParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            bottomMargin = 16
        }

        tasksContainer.addView(taskLayout, marginParams)
    }
}
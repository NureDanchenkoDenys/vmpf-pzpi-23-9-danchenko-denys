package com.example.lb3

object TaskRepository {

    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun getTasks(): List<Task> {
        return tasks
    }

    fun getTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    fun addTask(title: String, description: String) {
        tasks.add(
            Task(
                id = nextId++,
                title = title,
                description = description
            )
        )
    }

    fun updateTask(id: Int, title: String, description: String) {
        val task = getTaskById(id)

        task?.title = title
        task?.description = description
    }

    fun deleteTask(id: Int) {
        tasks.removeAll { it.id == id }
    }

    fun changeTaskStatus(id: Int, isCompleted: Boolean) {
        val task = getTaskById(id)

        task?.isCompleted = isCompleted
    }
}
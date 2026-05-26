function TaskDetails({ selectedTask }) {
  if (!selectedTask) {
    return null
  }

  return (
    <div className="details">
      <h2>Деталі задачі</h2>
      <p><b>ID:</b> {selectedTask.id}</p>
      <p><b>Назва:</b> {selectedTask.title}</p>
      <p><b>Опис:</b> {selectedTask.description}</p>
    </div>
  )
}

export default TaskDetails
function TaskList({ tasks, showDetails, startEdit, deleteTask }) {
  return (
    <div className="tasks">
      {
        tasks.map(task => (
          <div key={task.id} className="task-card">
            <h2>{task.title}</h2>
            <p>{task.description}</p>

            <div className="buttons">
              <button onClick={() => showDetails(task.id)}>Деталі</button>
              <button onClick={() => startEdit(task)}>Редагувати</button>
              <button onClick={() => deleteTask(task.id)}>Видалити</button>
            </div>
          </div>
        ))
      }
    </div>
  )
}

export default TaskList
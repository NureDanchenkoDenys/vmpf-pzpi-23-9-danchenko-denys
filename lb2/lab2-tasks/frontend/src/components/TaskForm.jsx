function TaskForm({
  title,
  description,
  setTitle,
  setDescription,
  editingTask,
  addTask,
  updateTask
}) {
  return (
    <div className="form">
      <input
        type="text"
        placeholder="Назва задачі"
        value={title}
        onChange={(event) => setTitle(event.target.value)}
      />

      <textarea
        placeholder="Опис задачі"
        value={description}
        onChange={(event) => setDescription(event.target.value)}
      />

      {
        editingTask ? (
          <button onClick={updateTask}>Зберегти зміни</button>
        ) : (
          <button onClick={addTask}>Додати задачу</button>
        )
      }
    </div>
  )
}

export default TaskForm
import { useEffect, useState } from 'react'
import './App.css'

import TaskForm from './components/TaskForm'
import TaskList from './components/TaskList'
import TaskDetails from './components/TaskDetails'

import {
  getTasks,
  getTaskById,
  createTask,
  updateTaskById,
  deleteTaskById
} from './api/tasksApi'

function App() {
  const [tasks, setTasks] = useState([])
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')
  const [selectedTask, setSelectedTask] = useState(null)
  const [editingTask, setEditingTask] = useState(null)

  const loadTasks = () => {
    getTasks().then(data => setTasks(data))
  }

  useEffect(() => {
    loadTasks()
  }, [])

  const addTask = () => {
    createTask({ title, description }).then(() => {
      setTitle('')
      setDescription('')
      loadTasks()
    })
  }

  const deleteTask = (id) => {
    deleteTaskById(id).then(() => {
      setSelectedTask(null)
      loadTasks()
    })
  }

  const showDetails = (id) => {
    getTaskById(id).then(data => setSelectedTask(data))
  }

  const startEdit = (task) => {
    setEditingTask(task)
    setTitle(task.title)
    setDescription(task.description)
  }

  const updateTask = () => {
    updateTaskById(editingTask.id, { title, description }).then(() => {
      setEditingTask(null)
      setTitle('')
      setDescription('')
      loadTasks()
    })
  }

  return (
    <div className="container">
      <h1>Система управління задачами</h1>

      <TaskForm
        title={title}
        description={description}
        setTitle={setTitle}
        setDescription={setDescription}
        editingTask={editingTask}
        addTask={addTask}
        updateTask={updateTask}
      />

      <TaskList
        tasks={tasks}
        showDetails={showDetails}
        startEdit={startEdit}
        deleteTask={deleteTask}
      />

      <TaskDetails selectedTask={selectedTask} />
    </div>
  )
}

export default App
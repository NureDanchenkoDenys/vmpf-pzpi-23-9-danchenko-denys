const express = require('express')
const cors = require('cors')

const app = express()

app.use(cors())
app.use(express.json())

let tasks = []

app.get('/tasks', (req, res) => {
    res.json(tasks)
})

app.get('/tasks/:id', (req, res) => {
    const id = Number(req.params.id)
    const task = tasks.find(task => task.id === id)

    if (!task) {
        return res.status(404).json({ message: 'Задачу не знайдено' })
    }

    res.json(task)
})

app.post('/tasks', (req, res) => {
    const { title, description } = req.body

    if (!title || !description) {
        return res.status(400).json({ message: 'Заповніть всі поля' })
    }

    const newTask = {
        id: Date.now(),
        title,
        description
    }

    tasks.push(newTask)

    res.status(201).json(newTask)
})

app.put('/tasks/:id', (req, res) => {
    const id = Number(req.params.id)
    const { title, description } = req.body

    const task = tasks.find(task => task.id === id)

    if (!task) {
        return res.status(404).json({ message: 'Задачу не знайдено' })
    }

    if (!title || !description) {
        return res.status(400).json({ message: 'Заповніть всі поля' })
    }

    task.title = title
    task.description = description

    res.json(task)
})

app.delete('/tasks/:id', (req, res) => {
    const id = Number(req.params.id)

    const taskExists = tasks.some(task => task.id === id)

    if (!taskExists) {
        return res.status(404).json({ message: 'Задачу не знайдено' })
    }

    tasks = tasks.filter(task => task.id !== id)

    res.json({ message: 'Задачу видалено' })
})

app.listen(3000, () => {
    console.log('Server started on port 3000')
})
const express = require('express')
const Student = require('../models/Student')

const router = express.Router()

router.get('/', async (req, res) => {
    const students = await Student.findAll()
    res.json(students)
})

router.post('/', async (req, res) => {
    const student = await Student.create(req.body)
    res.json(student)
})

router.put('/:id', async (req, res) => {
    const student = await Student.findByPk(req.params.id)

    if (!student) {
        return res.status(404).json({ message: 'Студента не знайдено' })
    }

    await student.update(req.body)
    res.json(student)
})

router.delete('/:id', async (req, res) => {
    const student = await Student.findByPk(req.params.id)

    if (!student) {
        return res.status(404).json({ message: 'Студента не знайдено' })
    }

    await student.destroy()
    res.json({ message: 'Студента видалено' })
})

module.exports = router
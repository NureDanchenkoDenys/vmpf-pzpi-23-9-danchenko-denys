const express = require('express')
const Grade = require('../models/Grade')
const Student = require('../models/Student')
const Course = require('../models/Course')

const router = express.Router()

router.get('/', async (req, res) => {
    const grades = await Grade.findAll({
        include: [Student, Course]
    })

    res.json(grades)
})

router.post('/', async (req, res) => {
    const grade = await Grade.create(req.body)
    res.json(grade)
})

router.put('/:id', async (req, res) => {
    const grade = await Grade.findByPk(req.params.id)

    if (!grade) {
        return res.status(404).json({ message: 'Оцінку не знайдено' })
    }

    await grade.update(req.body)
    res.json(grade)
})

router.delete('/:id', async (req, res) => {
    const grade = await Grade.findByPk(req.params.id)

    if (!grade) {
        return res.status(404).json({ message: 'Оцінку не знайдено' })
    }

    await grade.destroy()
    res.json({ message: 'Оцінку видалено' })
})

module.exports = router
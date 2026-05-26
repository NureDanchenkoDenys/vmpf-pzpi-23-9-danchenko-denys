const express = require('express')
const Course = require('../models/Course')

const router = express.Router()

router.get('/', async (req, res) => {
    const courses = await Course.findAll()
    res.json(courses)
})

router.post('/', async (req, res) => {
    const course = await Course.create(req.body)
    res.json(course)
})

router.put('/:id', async (req, res) => {
    const course = await Course.findByPk(req.params.id)

    if (!course) {
        return res.status(404).json({ message: 'Курс не знайдено' })
    }

    await course.update(req.body)

    res.json(course)
})

router.delete('/:id', async (req, res) => {
    const course = await Course.findByPk(req.params.id)

    if (!course) {
        return res.status(404).json({ message: 'Курс не знайдено' })
    }

    await course.destroy()

    res.json({ message: 'Курс видалено' })
})

module.exports = router
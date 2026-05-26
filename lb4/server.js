const express = require('express')
const cors = require('cors')

const sequelize = require('./database')

const Student = require('./models/Student')
const Teacher = require('./models/Teacher')
const Course = require('./models/Course')
const Lesson = require('./models/Lesson')
const Grade = require('./models/Grade')

const studentsRoutes = require('./routes/studentsRoutes')
const coursesRoutes = require('./routes/coursesRoutes')
const gradesRoutes = require('./routes/gradesRoutes')

const app = express()

app.use(cors())
app.use(express.json())

app.use('/students', studentsRoutes)
app.use('/courses', coursesRoutes)
app.use('/grades', gradesRoutes)

Teacher.hasMany(Course)
Course.belongsTo(Teacher)

Course.hasMany(Lesson)
Lesson.belongsTo(Course)

Teacher.hasMany(Lesson)
Lesson.belongsTo(Teacher)

Student.hasMany(Grade)
Grade.belongsTo(Student)

Course.hasMany(Grade)
Grade.belongsTo(Course)

app.get('/', (req, res) => {
    res.send('University API працює')
})

sequelize.sync({ alter: true })
    .then(() => {
        console.log('База даних підключена')

        app.listen(3000, () => {
            console.log('Сервер працює на порту 3000')
        })
    })
    .catch((error) => {
        console.log(error)
    })
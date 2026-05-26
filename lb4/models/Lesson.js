const { DataTypes } = require('sequelize')
const sequelize = require('../database')

const Lesson = sequelize.define('Lesson', {
    topic: {
        type: DataTypes.STRING,
        allowNull: false
    },

    date: {
        type: DataTypes.DATE,
        allowNull: false
    }
})

module.exports = Lesson
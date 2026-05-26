const { DataTypes } = require('sequelize')
const sequelize = require('../database')

const Course = sequelize.define('Course', {
    title: {
        type: DataTypes.STRING,
        allowNull: false
    },

    description: {
        type: DataTypes.TEXT
    }
})

module.exports = Course
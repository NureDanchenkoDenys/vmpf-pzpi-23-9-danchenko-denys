const { DataTypes } = require('sequelize')
const sequelize = require('../database')

const Teacher = sequelize.define('Teacher', {
    name: {
        type: DataTypes.STRING,
        allowNull: false
    },

    department: {
        type: DataTypes.STRING,
        allowNull: false
    }
})

module.exports = Teacher
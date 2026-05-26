const { DataTypes } = require('sequelize')
const sequelize = require('../database')

const Grade = sequelize.define('Grade', {
    value: {
        type: DataTypes.INTEGER,
        allowNull: false
    }
})

module.exports = Grade
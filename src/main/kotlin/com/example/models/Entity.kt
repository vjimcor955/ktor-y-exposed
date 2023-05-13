package com.example.models

import org.jetbrains.exposed.sql.*

data class Entity(val id: String, val value: String, val name: String, val description: String, val sectionId: String, val order: Int)

object Entities : Table() {
    val id = varchar("id", 32)
    val value = varchar("value", 1024)
    val name = varchar("name", 128)
    val description = varchar("description", 256)
    val sectionId = varchar("sectionId", 32) // .foreignKey()
    val order = integer("order")

    override val primaryKey = PrimaryKey(id)
}
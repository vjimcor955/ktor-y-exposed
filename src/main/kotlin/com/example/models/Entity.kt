package com.example.models

import com.example.models.Articles.autoIncrement
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

data class Entity(val id: Int, val value: String, val name: String, val description: String, val seasonId: String, val order: Int, val sectionId: Int)

object Entities : Table(){
    val id = integer("id").autoIncrement()
    val value = varchar("value", 1024)
    val name = varchar("name", 128)
    val description = varchar("description", 256)
    val seasonId = varchar("seasonId", 32)
    val order = integer("order")
    val sectionId = integer("sectionid").references(Articles.id, onDelete = ReferenceOption.CASCADE)

    override val primaryKey = PrimaryKey(id)
}
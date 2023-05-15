package com.example.dao

import com.example.models.*

interface DAOEntity {
    suspend fun allEntities(): List<Entity>
    suspend fun entity(id: Int): Entity?
    suspend fun addNewEntity(value: String, name: String, description: String, seasonId: String, order: Int): Entity?
    suspend fun editEntity(id: Int, value: String, name: String, description: String, seasonId: String, order: Int): Boolean
    suspend fun deleteEntity(id: Int): Boolean
}
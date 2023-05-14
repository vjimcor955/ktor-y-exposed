package com.example.dao

import com.example.models.*

interface DAOFacade {
    suspend fun allArticles(): List<Article>
    suspend fun article(id: Int): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean


    suspend fun allEntities(): List<Entity>
    suspend fun entity(id: String): Entity?
    suspend fun addNewEntity(id: String, value: String, name: String, description: String, sectionId: String, order: Int): Entity?
    suspend fun editEntity(id: String, value: String, name: String, description: String, sectionId: String, order: Int): Boolean
    suspend fun deleteEntity(id: String): Boolean
}
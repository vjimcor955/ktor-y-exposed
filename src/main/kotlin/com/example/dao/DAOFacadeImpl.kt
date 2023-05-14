package com.example.dao

import com.example.models.Article
import com.example.models.Entity
import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body],
    )

    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun article(id: Int): Article? = dbQuery {
        Articles
            .select { Articles.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean = dbQuery {
        Articles.update({ Articles.id eq id }) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }

    override suspend fun deleteArticle(id: Int): Boolean = dbQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }



    private fun resultRowToEntity(row: ResultRow) = Entity(
        id = row[Entities.id],
        value = row[Entities.value],
        name = row[Entities.name],
        description = row[Entities.description],
        sectionId = row[Entities.sectionId],
        order = row[Entities.order],
    )

    override suspend fun allEntities(): List<Entity> = dbQuery {
        Entities.selectAll().map(::resultRowToEntity)
    }

    override suspend fun entity(id: String): Entity? = dbQuery {
        Entities
            .select { Entities.id eq id }
            .map(::resultRowToEntity)
            .singleOrNull()
    }

    override suspend fun addNewEntity(id: String, value: String, name: String, description: String, sectionId: String, order: Int): Entity? = dbQuery {
        val insertStatement = Entities.insert {
            it[Entities.id] = id
            it[Entities.value] = value
            it[Entities.name] = name
            it[Entities.description] = description
            it[Entities.sectionId] = sectionId
            it[Entities.order] = order
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToEntity)
    }

    override suspend fun editEntity(id: String, value: String, name: String, description: String, sectionId: String, order: Int): Boolean = dbQuery {
        Articles.update({ Entities.id eq id }) {
            it[Entities.id] = id
            it[Entities.value] = value
            it[Entities.name] = name
            it[Entities.description] = description
            it[Entities.sectionId] = sectionId
            it[Entities.order] = order
        } > 0
    }

    override suspend fun deleteEntity(id: String): Boolean = dbQuery {
        Entities.deleteWhere { Entities.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allArticles().isEmpty()) {
            addNewArticle("The drive to develop!", "...it's what keeps me going.")
            addNewEntity("1", "Valor", "Ejemplo", "Descripcion", "SectionId", 1)
        }
    }
}

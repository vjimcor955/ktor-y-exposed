package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Entity
import com.example.models.Entities
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOEntityImpl : DAOEntity {
    private fun resultRowToEntity(row: ResultRow) = Entity(
        id = row[Entities.id],
        value = row[Entities.value],
        name = row[Entities.name],
        description = row[Entities.description],
        seasonId = row[Entities.seasonId],
        order = row[Entities.order],
        sectionId = row[Entities.sectionId]
    )
    override suspend fun allEntities(): List<Entity> = dbQuery {
        Entities.selectAll().map(::resultRowToEntity)
    }

    override suspend fun entity(id: Int): Entity? = dbQuery {
        Entities
            .select { Entities.id eq id }
            .map(::resultRowToEntity)
            .singleOrNull()
    }

    override suspend fun addNewEntity(
        value: String,
        name: String,
        description: String,
        seasonId: String,
        order: Int,
        sectionId: Int
    ): Entity? = dbQuery {
        val insertStatement = Entities.insert {
            it[Entities.value] = value
            it[Entities.name] = name
            it[Entities.description] = description
            it[Entities.seasonId] = seasonId
            it[Entities.order] = order
            it[Entities.sectionId] = sectionId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToEntity)
    }

    override suspend fun editEntity(
        id: Int,
        value: String,
        name: String,
        description: String,
        seasonId: String,
        order: Int,
        sectionId: Int
    ): Boolean = dbQuery {
        Entities.update({ Entities.id eq id }) {
            it[Entities.value] = value
            it[Entities.name] = name
            it[Entities.description] = description
            it[Entities.seasonId] = seasonId
            it[Entities.order] = order
            it[Entities.sectionId] = sectionId
        } > 0
    }

    override suspend fun deleteEntity(id: Int): Boolean = dbQuery {
        Entities.deleteWhere { Entities.id eq id } > 0
    }

    override suspend fun showEntity(id: Int): List<Entity> = dbQuery {
        Entities
            .select { Entities.sectionId eq id }
            .map(::resultRowToEntity)
    }
}
val daoEntity: DAOEntity = DAOEntityImpl().apply {
    runBlocking {
        if(allEntities().isEmpty()) {
            addNewEntity("1", "Nombre", "Descripcion", "SeasonId", 1, 1)
        }
    }
}
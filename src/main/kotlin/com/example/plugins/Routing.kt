package com.example.plugins

import com.example.dao.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*


fun Application.configureRouting() {
    routing {
        // ...
        get("/") {
            call.respondRedirect("articles")
        }
        route("articles") {
            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to dao.allArticles())))
            }
            get("new") {
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val article = dao.addNewArticle(title, body)
                call.respondRedirect("/articles/${article?.id}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("show.ftl", mapOf("article" to dao.article(id))))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to dao.article(id))))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        dao.editArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        dao.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
        route("entities") {
            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("entities" to daoEntity.allEntities())))
            }

            get("new") {
                call.respond(FreeMarkerContent("ent_new.ftl", model = null))
            }
            post {
                val formParameters = call.receiveParameters()
                val value = formParameters.getOrFail("value")
                val name = formParameters.getOrFail("name")
                val description = formParameters.getOrFail("description")
                val seasonId = formParameters.getOrFail("seasonId")
                val order = formParameters.getOrFail<Int>("order").toInt()
                val entity = daoEntity.addNewEntity(value, name, description, seasonId, order)
                call.respondRedirect("/entities/${entity?.id}")
            }

            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("ent_show.ftl", mapOf("entity" to daoEntity.entity(id))))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("ent_edit.ftl", mapOf("entity" to daoEntity.entity(id))))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val value = formParameters.getOrFail("value")
                        val name = formParameters.getOrFail("name")
                        val description = formParameters.getOrFail("description")
                        val seasonId = formParameters.getOrFail("seasonId")
                        val order = formParameters.getOrFail<Int>("order").toInt()
                        daoEntity.editEntity(id, value, name, description, seasonId, order)
                        call.respondRedirect("/entities/$id")
                    }
                    "delete" -> {
                        daoEntity.deleteEntity(id)
                        call.respondRedirect("/entities")
                    }
                }
            }
        }
    }
}
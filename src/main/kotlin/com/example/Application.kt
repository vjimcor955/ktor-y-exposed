package com.example

import freemarker.cache.*
import io.ktor.server.freemarker.*
import io.ktor.server.application.*
import com.example.plugins.*
import com.example.dao.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    configureRouting()
    configureTemplating()
}

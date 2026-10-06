package com.oneotrixwork.sportcrm

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

fun main() {
    initDatabase()

    embeddedServer(Netty, port = 8080, ) {
        install(ContentNegotiation) {
            json()
        }


        routing {

            post("/login") {
                val request = call.receive<LoginRequest>()

                val userRow = transaction {
                    UserTable.selectAll()
                        .where { UserTable.login eq request.login }
                        .singleOrNull()
                }

                if (userRow == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = LoginResponse(false, "Неверный логин или пароль")
                    )
                    return@post
                }

                val savedHash = userRow[UserTable.passwordHash]
                val isPasswordCorrect = BCrypt.checkpw(request.password, savedHash)

                if (!isPasswordCorrect) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = LoginResponse(false, "Неверный логин или пароль")
                    )
                    return@post
                }

                val userRole = userRow[UserTable.role].name

                call.respond(
                    HttpStatusCode.OK,
                    LoginResponse(
                        success = true,
                        message = "Вход выполнен успешно",
                        role = userRole,
                        token = "mock_jwt_token"
                    )
                )
            }
        }
    }.start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText(("Ktor"))
        }
    }
}
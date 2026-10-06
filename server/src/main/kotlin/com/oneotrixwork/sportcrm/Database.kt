package com.oneotrixwork.sportcrm

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

enum class UserRole {
    COACH, STUDENT, CANDIDATE
}

object UserTable : Table("users") {
    val id = uuid("id").autoGenerate()
    val login = varchar("login", 50).uniqueIndex()
    val passwordHash = varchar("password_hash", 100)
    val role = enumerationByName("role", 20, UserRole::class)

    override val primaryKey = PrimaryKey(id)
}


fun initDatabase() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/sport_crm_db",
        driver = "org.postgresql.Driver",
        user = "oneotrix_sport_crm",
        password = "klan2789@"
    )

    transaction {
        SchemaUtils.create(UserTable)

        if(UserTable.selectAll().where { UserTable.login eq "coach_tengo" }.empty()) {
            val temporaryHash = BCrypt.hashpw("123456", BCrypt.gensalt())
            UserTable.insert {
                it[login] = "coach_tengo"
                it[passwordHash] = temporaryHash
                it[role] = UserRole.COACH
            }
        }
    }
}
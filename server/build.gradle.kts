plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.oneotrixwork.sportcrm"
version = "1.0.0"

kotlin {
    jvmToolchain(17)
}

application {
    mainClass = "com.oneotrixwork.sportcrm.ApplicationKt"
}

dependencies {
    // Ktor
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    implementation(libs.ktor.serialization.kotlinx)
    implementation(libs.ktor.server.content.negotiation)

    // Jetbrains for database
    implementation(libs.jetbrains.exposed.core)
    implementation(libs.jetbrains.exposed.dao)
    implementation(libs.jetbrains.exposed.jdbc)

    // PostgreSQL driver
    implementation(libs.postgresql.driver)

    //hashing
    implementation(libs.jbcrypt)

    // Test
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}
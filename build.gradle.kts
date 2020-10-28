import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "me.roridev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "MainKt"
}
dependencies {
    implementation ("org.seleniumhq.selenium:selenium-java:4.0.0-alpha-6")
    implementation("org.seleniumhq.selenium:selenium-api:4.0.0-alpha-6")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:4.0.0-alpha-6")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.0.0-alpha-6")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}
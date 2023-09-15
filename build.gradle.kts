plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "badr"
version = "1.0"

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.fusesource.jansi:jansi:2.4.0")
    implementation ("org.jline:jline:3.21.0")
    implementation("com.github.ajalt.clikt:clikt:4.2.0")
    implementation("com.github.ajalt.mordant:mordant:2.1.0")
    implementation("com.github.kotlin-inquirer:kotlin-inquirer:0.1.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
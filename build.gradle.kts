plugins {
    kotlin("jvm") version "2.2.21"
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "aarabdh.ao3integration"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jsoup:jsoup:1.17.2")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}
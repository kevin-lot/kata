group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
}
plugins {
    id("com.diffplug.spotless") version "8.3.0"
    kotlin("jvm") version "2.3.0"
}
repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}
spotless {
    kotlin {
        ktlint()
        target("src/**/*.kt")
    }
}

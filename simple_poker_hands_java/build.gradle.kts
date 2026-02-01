group = "local.kata"
version = "1.0-SNAPSHOT"

// dependencies
dependencies {
    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
plugins {
    id("com.diffplug.spotless") version "8.3.0"
    kotlin("jvm") version "2.3.0"
}
repositories {
    mavenCentral()
}

// configuration
kotlin {
    jvmToolchain(21)
}
spotless {
    java {
        palantirJavaFormat() // or googleJavaFormat()
        target("src/**/*.java")
    }
}
tasks.test {
    useJUnitPlatform()
}

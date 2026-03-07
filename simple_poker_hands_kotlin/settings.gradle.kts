//Some plugins are “settings-level”. These affect:
// - How Gradle resolves toolchains or configures the build itself before any project is evaluated.
// - Gradle dependency resolution for plugins or toolchains.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "simple_poker_hands_kotlin"

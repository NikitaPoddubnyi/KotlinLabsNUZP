plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.compose") version Versions.compose
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("file://${rootDir}/.m2repo/")
}

dependencies {
    implementation(Versions.library)
    implementation(compose.desktop.currentOs)
    implementation(project(":caffe"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(Versions.jvmLevel)
}

// ADD THESE LINES TO FIX JVM TARGET COMPATIBILITY
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = Versions.jvmLevel.toString()
}

tasks.withType<JavaCompile>().configureEach {
    targetCompatibility = Versions.jvmLevel.toString()
    sourceCompatibility = Versions.jvmLevel.toString()
}

application {
    mainClass.set("MainKt")
}
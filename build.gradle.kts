import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
}

apply { plugin("org.jetbrains.kotlin.jvm") }
group = "com.github.ivan.kopylove"
version = "snapshot"

repositories {
    mavenCentral()
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

        testImplementation("org.hamcrest:hamcrest-all:1.3")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
        testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.0.0")

        testImplementation("org.awaitility:awaitility-kotlin:4.2.0")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}



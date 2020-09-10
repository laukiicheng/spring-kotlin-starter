import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin
    id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = false
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "com.revelhealth.kotlinspringservice"
    version = "1.0-SNAPSHOT"

    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        jcenter()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloud}")
            mavenBom("org.springframework.boot:spring-boot:${Versions.springBoot}")
        }
    }

    ktlint {
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        disabledRules.set(listOf("no-wildcard-imports"))
        this.version.set("0.38.1") // Explicit version to support kotlin 1.4
    }
}

subprojects {

    dependencies {
        // logging extension library to allow for kotlin idiomatic logging conventions
        implementation("io.github.microutils:kotlin-logging:1.8.3")

        // mongo db
        implementation("org.springframework.data:spring-data-mongodb")

        // using Mockk as our mocking framework: https://github.com/mockk/mockk
        testImplementation("io.mockk:mockk:1.10.0")

        testImplementation("org.springframework:spring-test")

        // comprehensive kotlin test framework, more info at https://github.com/kotlintest/kotlintest
        testImplementation("io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}")
        testImplementation("io.kotest:kotest-assertions-core-jvm:${Versions.kotest}")
    }

    tasks.withType<KotlinCompile> {
        println("Configuring $name in project ${project.name}...")
        kotlinOptions {
            languageVersion = "1.4"
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

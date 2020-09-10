plugins {
    idea
}

apply(plugin = "org.springframework.boot")
apply<IdeaPlugin>()

sourceSets.create("integrationTest") {
    compileClasspath += sourceSets["main"].output + configurations.testCompile
    compileClasspath += sourceSets["test"].output + configurations.testCompile
    runtimeClasspath += output + compileClasspath + configurations.testRuntime
    resources.srcDir("src/integrationTest/resources")
}

configurations {
    this["integrationTestImplementation"].extendsFrom(this["testImplementation"])
    this["integrationTestRuntime"].extendsFrom(this["testRuntime"])
}
val integrationTestImplementation = configurations["integrationTestImplementation"]!!

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.retry:spring-retry")
    implementation("org.springframework:spring-webmvc")

    integrationTestImplementation("io.kotest:kotest-extensions-spring:${Versions.kotest}")
    integrationTestImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
    integrationTestImplementation("org.springframework:spring-test")
    integrationTestImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.4.1")
}

val integrationTestResultsLocation = "reports/test/integration-tests"
tasks.register<Test>("integrationTest") {
    useJUnitPlatform()
    description = "Runs integration tests"
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    outputs.upToDateWhen { false }
    mustRunAfter("test")
    reports.html.destination = file("$buildDir/$integrationTestResultsLocation")
    reports.junitXml.destination = file("$buildDir/$integrationTestResultsLocation")

    testLogging {
        showStandardStreams = false
        setExceptionFormat("full")
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
        )
    }

    val includeTagsKey = "kotlintest.tags.include"
    System.getProperty(includeTagsKey)?.run {
        println("Running integration tests with include tag $this")
        systemProperty(includeTagsKey, this)
    }

    val excludeTagsKey = "kotlintest.tags.exclude"
    System.getProperty(excludeTagsKey)?.run {
        println("Running integration tests with exclude tag $this")
        systemProperty(excludeTagsKey, this)
    }
}

tasks["check"].dependsOn("integrationTest")

idea.module {
    testSourceDirs = testSourceDirs.plus(project.sourceSets["integrationTest"].allSource.srcDirs)
}

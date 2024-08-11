import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
    java
}

repositories {
    mavenCentral()
}

sourceSets {
    test {
        java {
            srcDir("src/main/java")
        }
    }
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.6.2")
    implementation("org.assertj:assertj-core:3.16.1")
}

tasks.withType<JavaCompile> {
    options.apply {
        release.set(11)
        compilerArgs = listOf("-Xlint:all")
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events(PASSED, SKIPPED, FAILED)
        showStandardStreams = true
    }
}

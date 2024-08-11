import org.gradle.api.tasks.testing.logging.TestLogEvent.*

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
    implementation("org.junit.jupiter:junit-jupiter:5.7.2")
    implementation("org.assertj:assertj-core:3.20.2")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.apply {
            release.set(11)
            compilerArgs = listOf("-Xlint:all")
        }
    }

    test {
        useJUnitPlatform()

        testLogging {
            events(PASSED, SKIPPED, FAILED)
            showStandardStreams = true
        }
    }
}

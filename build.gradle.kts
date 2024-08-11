import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    java
}

group = "com.att.training.generics"
version = "0.0.1-SNAPSHOT"

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
    val junitJupiter = "5.10.3"
    val assertj = "3.26.3"
    implementation("org.junit.jupiter:junit-jupiter:$junitJupiter")
    implementation("org.assertj:assertj-core:$assertj")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.apply {
            release.set(21)
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

plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
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
    options.compilerArgs = listOf("-Xlint:all")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("skipped", "failed")
        showStandardStreams = true
    }
}

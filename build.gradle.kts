plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation("org.junit.jupiter:junit-jupiter:5.5.2")
    implementation("org.assertj:assertj-core:3.14.0")
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Xlint:all")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.6"
    id("maven-publish")
}

group = "me.letsdev"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.springframework:spring-web")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.github.merge-simpson"
            artifactId = project.name
            version = project.version.toString()
        }
    }
}

tasks.named("publishToMavenLocal").configure {
    dependsOn("assemble")
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
    archiveClassifier.set("") // remove suffix "-plain"
    // 다운로드 하려는 파일 이름: letsdev-error-code-api-0.1.0.jar
    // 위 명령 누락 때 파일 이름: letsdev-error-code-api-0.1.0-plain.jar
}
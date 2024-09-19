plugins {
    id("java")
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("maven-publish")
}

group = "me.letsdev"
version = "0.1.0"

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
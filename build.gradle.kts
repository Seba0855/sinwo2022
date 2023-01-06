import io.ktor.plugin.features.*

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

group = "pl.edu.smcebi"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.8.0"
    id("io.ktor.plugin") version "2.2.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}

ktor {
    docker {
        // Konfiguracja wersji JRE wykorzystywanej przez obraz
        jreVersion.set(JreVersion.JRE_17)

        // Ustawienie nazwy obrazu kontenera wraz z jego tagiem. Jako tag podajemy wersje zdefiniowaną wyżej w projekcie.
        localImageName.set("sinwo_kotlin_backend")
        imageTag.set(version as String)

        portMappings.set(
            listOf(
                DockerPortMapping(
                    // Przemapowanie portu 8080 (domyślnego dla Dockera) na zewnętrzny port 80
                    // Pozwala nam to na uruchomienie serwera pod adresem http://0.0.0.0:80
                    insideDocker = 8080,
                    outsideDocker = 80,
                    protocol = DockerPortMappingProtocol.TCP
                )
            )
        )

        // Ta część pozwala nam na opublikowanie kontenera na DockerHubie
        // korzystając ze zmiennych środowiskowych DOCKER_HUB_USERNAME oraz DOCKER_HUB_PASSWORD
        externalRegistry.set(
            DockerImageRegistry.dockerHub(
                appName = provider { "sinwo-kotlin-backend" },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )
    }
}

application {
    mainClass.set("pl.edu.smcebi.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-openapi:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auto-head-response-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
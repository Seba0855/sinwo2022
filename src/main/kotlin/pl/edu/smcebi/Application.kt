package pl.edu.smcebi

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import pl.edu.smcebi.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureRouting()
}

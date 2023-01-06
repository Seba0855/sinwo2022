package pl.edu.smcebi.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import pl.edu.smcebi.routes.customerRouting
import pl.edu.smcebi.routes.getOrderRoute
import pl.edu.smcebi.routes.listOrdersRoute
import pl.edu.smcebi.routes.totalizeOrderRoute

fun Application.configureRouting() {
    
    install(AutoHeadResponse)

    routing {
        customerRouting()
        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}

package pl.edu.smcebi.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(val number: String, val contents: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

val orderStorage = mutableListOf(Order(
    "2023-01-15-01", listOf(
        OrderItem("Kanapka z szynką", 2, 5.50),
        OrderItem("Woda", 1, 1.50),
        OrderItem("Piwo", 3, 2.30),
        OrderItem("Sernik", 1, 3.75)
    )),
    Order("2023-01-15-02", listOf(
        OrderItem("Cheeseburger", 1, 8.50),
        OrderItem("Woda", 2, 1.50),
        OrderItem("Cola", 2, 1.76),
        OrderItem("Lody karpatkowe", 1, 2.35)
    ))
)
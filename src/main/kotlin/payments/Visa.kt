package main.kotlin.payments

import java.time.LocalTime

class Visa : PaymentMethod() {

    override fun calculateCommission(amount: Double): Double {

        val now = LocalTime.now()

        val start = LocalTime.of(15, 0)
        val end = LocalTime.of(22, 30)

        return if (now >= start && now <= end) {
            amount * 0.01
        } else {
            amount * 0.03
        }
    }
}
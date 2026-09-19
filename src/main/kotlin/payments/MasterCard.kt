package main.kotlin.payments

import java.time.DayOfWeek
import java.time.LocalDate

class MasterCard : PaymentMethod() {

    override fun calculateCommission(amount: Double): Double {

        val day = LocalDate.now().dayOfWeek

        return if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            amount * 0.03
        } else {
            amount * 0.0075
        }
    }
}
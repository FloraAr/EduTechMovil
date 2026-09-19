package main.kotlin.services

import main.kotlin.data.Course
import main.kotlin.data.User
import main.kotlin.exceptions.SaldoInsuficienteException
import main.kotlin.payments.PaymentMethod

class PurchaseService {

    fun calculateSubtotal(courses: List<Course>): Double {

        return courses.size * 10000.0
    }

    fun calculateCommission(
        courses: List<Course>,
        paymentMethod: PaymentMethod
    ): Double {

        val subtotal = calculateSubtotal(courses)

        return paymentMethod.calculateCommission(subtotal)
    }

    fun calculateTotal(
        courses: List<Course>,
        paymentMethod: PaymentMethod
    ): Double {

        val subtotal = calculateSubtotal(courses)

        val commission = paymentMethod.calculateCommission(subtotal)

        return subtotal + commission
    }

    fun purchase(
        user: User,
        courses: List<Course>,
        paymentMethod: PaymentMethod
    ): Double {

        val total = calculateTotal(courses, paymentMethod)

        if (user.money < total) {
            throw SaldoInsuficienteException()
        }

        user.money -= total

        return total
    }
}
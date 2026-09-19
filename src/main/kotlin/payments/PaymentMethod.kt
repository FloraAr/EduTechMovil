package main.kotlin.payments

//OPEN para que pueda ser heredada
open class PaymentMethod {

    open fun calculateCommission(amount: Double): Double {
        return 0.0
    }
}
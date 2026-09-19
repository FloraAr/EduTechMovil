package main.kotlin.payments

class MercadoPago : PaymentMethod() {

    override fun calculateCommission(amount: Double): Double {
        return amount * 0.02
    }
}
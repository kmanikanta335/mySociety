package com.society.repository

import com.society.model.Payment
import kotlinx.coroutines.delay

class PaymentRepository {
    suspend fun makePayment(payment: Payment): Result<String> {
        // Simulate a payment processing logic
        return try {
            // Here you might call an API or a payment SDK
            // For demonstration, we'll simulate success
            if (payment.amount <= 0) {
                throw Exception("Invalid amount")
            }
            delay(1000) // Simulating network delay
            Result.success("Payment of ₹${payment.amount} successful to UPI: ${payment.upiId}")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
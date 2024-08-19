package com.society.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.society.model.Payment
import com.society.repository.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel() : ViewModel() {
    private val paymentRepository= PaymentRepository()
    private val _paymentStatus = MutableStateFlow<String>("")
    val paymentStatus: StateFlow<String> = _paymentStatus

    fun processPayment(amount: Double, phoneNumber: String, upiId: String) {
        val payment = Payment(amount, phoneNumber, upiId)
        viewModelScope.launch {
            val result = paymentRepository.makePayment(payment)
            _paymentStatus.value = result.getOrElse { "Payment failed: ${it.message}" }
        }
    }
}
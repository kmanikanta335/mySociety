package com.society.viewModel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.society.model.Payment
import com.society.repository.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel(application: Application, private val repository: PaymentRepository) : AndroidViewModel(application) {

    private val _paymentResult = MutableLiveData<String>()
    val paymentResult: LiveData<String> = _paymentResult

    fun initiatePayment(amount: String, upiId: String, name: String, note: String): Intent {
        return repository.getUPIIntent(amount, upiId, name, note)
    }

    fun handlePaymentResult(data: Intent?) {
        _paymentResult.value = repository.parseUPIResponse(data)
    }
}
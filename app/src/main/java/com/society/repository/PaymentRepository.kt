package com.society.repository

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.society.model.Payment
import com.society.viewModel.PaymentViewModel
import kotlinx.coroutines.delay

class PaymentRepository {
    fun getUPIIntent(amount: String, upiId: String, name: String, note: String): Intent {
        val upiUri = Uri.parse("upi://pay?pa=$upiId&pn=$name&tn=$note&am=$amount&cu=INR")
        return Intent(Intent.ACTION_VIEW, upiUri)
    }

    fun parseUPIResponse(data: Intent?): String {
        val responseText = data?.getStringExtra("response")
        return when {
            responseText == null -> "Transaction canceled by user"
            responseText.contains("SUCCESS", true) -> "Transaction successful"
            responseText.contains("FAILURE", true) -> "Transaction failed"
            responseText.contains("SUBMITTED", true) -> "Transaction submitted"
            else -> "Unknown response"
        }
    }
}

class PaymentViewModelFactory(private val repository: PaymentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PaymentViewModel(Application(), repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
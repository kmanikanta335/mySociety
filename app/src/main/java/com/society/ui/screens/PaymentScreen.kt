package com.society.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.society.viewModel.PaymentViewModel

@Composable
fun PaymentScreen() {
    val paymentViewModel: PaymentViewModel = viewModel()

    var amount by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var upiId by remember { mutableStateOf("") }
    val paymentStatus by paymentViewModel.paymentStatus.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
                )
        )

        TextField(
            value = upiId,
            onValueChange = { upiId = it },
            label = { Text("UPI ID") }
        )

        Button(onClick = {
            val amt = amount.toDoubleOrNull()
            if (amt != null) {
                paymentViewModel.processPayment(amt, phoneNumber, upiId)
            } else {
                // Handle invalid amount input
            }
        }) {
            Text( text ="Make Payment")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = paymentStatus) // Display payment status
    }
}
package com.society.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.society.R
import com.society.repository.PaymentRepository
import com.society.repository.PaymentViewModelFactory
import com.society.viewModel.PaymentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = viewModel(
    factory = PaymentViewModelFactory(PaymentRepository())
    )
    ) {
        var amount by remember { mutableStateOf("") }
        var upiId by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var note by remember { mutableStateOf("") }

        val paymentResult by viewModel.paymentResult.observeAsState()

        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            viewModel.handlePaymentResult(result.data)
        }

      val textFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.White,
        unfocusedIndicatorColor = Color.Black,
        disabledIndicatorColor = Color.Black
    )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.paytm),
                    contentDescription = "Paytm",
                    modifier = Modifier.size(94.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.gpay),
                    contentDescription = "Google Pay",
                    modifier = Modifier.size(94.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.phpay),
                    contentDescription = "PhonePe",
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))



            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter Amount") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = upiId,
                onValueChange = { upiId = it },
                label = { Text("Enter UPI ID") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter Name") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Enter Note") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val intent = viewModel.initiatePayment(amount, upiId, name, note)
                    launcher.launch(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "SEND BY UPI", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            paymentResult?.let {
                Text(text = "Payment Result: $it", color = Color.Black, fontSize = 16.sp)
            }
        }
}
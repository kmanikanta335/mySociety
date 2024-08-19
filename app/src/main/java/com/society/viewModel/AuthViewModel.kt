package com.society.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.society.repository.AuthRepository

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    private val _registrationResult = MutableLiveData<Task<AuthResult>>()
    val registrationResult: LiveData<Task<AuthResult>> get() = _registrationResult

    private val _loginResult = MutableLiveData<Task<AuthResult>>()
    val loginResult: LiveData<Task<AuthResult>> get() = _loginResult

    fun register(email: String, password: String) {
        val task = authRepository.register(email, password)
        _registrationResult.postValue(task)
    }

    fun login(email: String, password: String) {
        val task = authRepository.login(email, password)
        _loginResult.postValue(task)
    }

    fun logout() {
        authRepository.logout()
    }

    fun currentUser(): FirebaseUser? {
        return authRepository.currentUser()
    }
}
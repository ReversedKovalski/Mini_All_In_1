package com.example.drrrtesting.loginAct

interface LoginActView {
    fun showSuccess()
    fun showError(message: Int)
    fun updateUItoLogin()
    fun updateUItoRegister()
    fun startNewAct()
}
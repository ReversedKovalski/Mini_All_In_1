package com.example.drrrtesting.loginAct

interface LoginActPresenter {
    fun login(email: String, password: String)
    fun createAccount(name: String, email: String, password: String)
    fun activityDead()
}
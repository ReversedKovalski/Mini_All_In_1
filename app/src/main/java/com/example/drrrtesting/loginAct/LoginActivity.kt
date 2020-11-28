package com.example.drrrtesting.loginAct

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.drrrtesting.R
import com.example.drrrtesting.mainAct.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), LoginActView {

    @Inject
    lateinit var mPresenter: LoginActPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        txtRegLA.setOnClickListener {
            updateUItoRegister()
        }
        btnLoginLA.setOnClickListener {
            login()
        }
        btnRegisterLA.setOnClickListener {
            register()
        }
    }
    private fun login(){
        val email: String = edtEmailLA.text.toString()
        val password: String = edtPasswordLA.text.toString()
        mPresenter.login(email, password)
    }
    private fun register(){
        val name: String = edtNameLA.text.toString()
        val email: String = edtEmailLA.text.toString()
        val password: String = edtPasswordLA.text.toString()
        mPresenter.createAccount(name, email, password)
    }

    override fun showSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_LONG).show()
    }

    override fun updateUItoLogin() {
        btnRegisterLA.visibility = GONE
        edtNameLA.visibility = GONE
        btnLoginLA.visibility = VISIBLE
        txtRegLA.visibility = VISIBLE
    }

    override fun updateUItoRegister() {
        btnRegisterLA.visibility = VISIBLE
        edtNameLA.visibility = VISIBLE
        txtRegLA.visibility = GONE
        btnLoginLA.visibility = GONE
    }

    override fun startNewAct() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.activityDead()
    }
}
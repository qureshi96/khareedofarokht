package com.example.kd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kd.R
import android.view.View
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.example.kd.MainActivity
import com.example.kd.Signup
import com.example.kd.FindAccount
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signupLink = findViewById<Button>(R.id.signup)
        val nextButton = findViewById<Button>(R.id.complete_login)

        val email = findViewById<TextInputLayout>(R.id.email)
        val password = findViewById<TextInputLayout>(R.id.password1)

        signupLink.setOnClickListener {
            callSignup()
        }

        nextButton.setOnClickListener {
            val emailText = email.editText!!.text.toString()
            val password = password.editText!!.text.toString()

            login(emailText, password)
        }
    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        println("CURRRENTT USERRRR $currentUser")
        if (currentUser != null) {
            callMainActivity()
            finish()
        }
    }

    private fun callMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun callSignup() {
        val i = Intent(this, Signup::class.java)
        startActivity(i)
        finish()
    }

    fun call_forgot_password(view: View?) {
        val i = Intent(this, FindAccount::class.java)
        startActivity(i)
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    callMainActivity()
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }
}
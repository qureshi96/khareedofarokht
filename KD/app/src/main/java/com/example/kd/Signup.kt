package com.example.kd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.kd.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val loginLink = findViewById<Button>(R.id.login)
        val signupButton = findViewById<Button>(R.id.call_main_activity)

        val emailLayout = findViewById<TextInputLayout>(R.id.email)
        val passwordLayout = findViewById<TextInputLayout>(R.id.password)
        val firstnameLayout = findViewById<TextInputLayout>(R.id.first_name)
        val lastnameLayout = findViewById<TextInputLayout>(R.id.last_name)

        // Initialize Firebase Auth
        auth = Firebase.auth

        signupButton.setOnClickListener {

            val email = emailLayout.editText!!.text.toString()
            val password = passwordLayout.editText!!.text.toString()
            val firstName = firstnameLayout.editText!!.text.toString()
            val lastName = lastnameLayout.editText!!.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val profileUpdates = userProfileChangeRequest {
                            displayName = "$firstName $lastName"
                        }

                        callMainActivity()
                        finish()

                        println("Signup complete!")
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

        loginLink.setOnClickListener{
            val i = Intent(this, Login::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun callMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}
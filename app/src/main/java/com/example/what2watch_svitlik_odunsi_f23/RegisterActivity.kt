package com.example.what2watch_svitlik_odunsi_f23

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.what2watch_svitlik_odunsi_f23.R.layout.activity_register
import com.example.what2watch_svitlik_odunsi_f23.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userRef: DatabaseReference

    private fun registerUser(email: String, password: String, fullName: String) {

    }


    fun updateUI(user: FirebaseUser?) {
        if (user != null) {

            val intent = Intent(this, HomeFragment::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            //    finish()
            //} else {

            // Toast.makeText(this, "Please try again with different credentials.", Toast.LENGTH_SHORT)
            //      .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(activity_register)

        firebaseAuth = FirebaseAuth.getInstance()
        userRef = FirebaseDatabase.getInstance().reference
        //database = FirebaseDatabase.getInstance()


        val registerButton: Button = findViewById(R.id.Registerbutton)
        registerButton.setOnClickListener {
            val email: String = findViewById<EditText>(R.id.editEmailAddress).text.toString()
            val password: String = findViewById<EditText>(R.id.editPassword).text.toString()
            val fullName: String = findViewById<EditText>(R.id.FullName).text.toString()


            if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                registerUser(email, password, fullName)
            }
        }


        fun registerUser(email: String, password: String) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = firebaseAuth.currentUser


                        // Save the user data to Firebase Database
                        user?.let { user ->
                            val userId = user.uid
                            val fullName = "fullName"



                            val userRegistrationReference = userRef.child("UserRegistration")
                            val userData = mapOf(
                                "uid" to userId,
                                "email" to email,
                                "fullName" to fullName
                            )
                            userRegistrationReference.child(userId).setValue(userData)
                        }

                        updateUI(firebaseAuth.currentUser)
                    } else {

                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }

    }
}
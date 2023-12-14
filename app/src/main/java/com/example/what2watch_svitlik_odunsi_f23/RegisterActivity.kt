package com.example.what2watch_svitlik_odunsi_f23

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.what2watch_svitlik_odunsi_f23.R.layout.activity_register
import com.example.what2watch_svitlik_odunsi_f23.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Suppress("NAME_SHADOWING")
class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userRef: DatabaseReference


    private fun registerUser(email: String, password: String, fullName: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmailAndPassword:success")
                    val user = auth.currentUser
                    updateUI(user)

                    // Save the user data to Firebase Database
                    user?.let { user ->
                        val userId = user.uid
                        val userRegistrationReference = userRef.child("UserRegistration")
                        val userData = mapOf(
                            "uid" to userId,
                            "fullName" to fullName
                        )
                        userRegistrationReference.child(userId).setValue(userData)
                    }
                } else {
                    Log.w(TAG, "createUserWithEmailAndPassword: failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication Failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

                private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Correct way to navigate to HomeFragment
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, HomeFragment())
            fragmentTransaction.addToBackStack(null) // Optional, to add the fragment to the back stack
            fragmentTransaction.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(activity_register)

        auth = Firebase.auth
        userRef = FirebaseDatabase.getInstance().reference

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

    }
}
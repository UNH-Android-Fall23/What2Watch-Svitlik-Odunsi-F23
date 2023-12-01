package com.example.what2watch_svitlik_odunsi_f23

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.what2watch_svitlik_odunsi_f23.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userRef: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        userRef = FirebaseDatabase.getInstance().reference

        val registerButton: Button = findViewById(R.id.Registerbutton)
        registerButton.setOnClickListener {
            val email: String = findViewById<EditText>(R.id.editEmailAddress).text.toString()
            val password: String = findViewById<EditText>(R.id.editPassword).text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    // Get the user's gender and country from the spinners
                    val selectedGender: String = genderSpinner.selectedItem.toString()
                    val selectedCountry: String = countrySpinner.selectedItem.toString()

                    // Save the user data to Firebase Database
                    user?.let { user ->
                        val userId = user.uid
                        val userData = mapOf(
                            "uid" to userId,
                            "email" to email,
                            "gender" to selectedGender,
                            "country" to selectedCountry
                        )
                        userRef.child(userId).setValue(userData)
                    }

                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // navigate to the home fragment
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        } else {
            // Prompt the user to try again with different credentials
            Toast.makeText(this, "Please try again with different credentials.", Toast.LENGTH_SHORT).show()
        }
    }
}
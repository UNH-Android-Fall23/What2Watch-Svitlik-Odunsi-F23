package com.example.what2watch_svitlik_odunsi_f23

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.ads.mediationtestsuite.activities.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val genderOptions=arrayOf("Male","Female","Non-Binary")
        auth = Firebase.auth
       userRef = Firebase.database

        //Citation :Android Studio Documentation.
        val genderSpinner = findViewById<Spinner>(R.id.genderSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_options,
            android.R.layout.simple_spinner_item)
            .also{
                adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                genderSpinner.adapter=adapter
            }

        val countrySpinner = findViewById<Spinner>(R.id.countrySpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.country_options,
            android.R.layout.simple_spinner_item)
            .also{
                    adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                genderSpinner.adapter=adapter
            }



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

                    // Get the use val selectedCountry: String =countrySpinner.selectedItem.toString()

                    // Save the user data to Firebase Database
                    user?.let { user ->
                        val userId = user.uid
                        val selectedGender = "Female, Male, Non-Binary"
                        //val userRegistrationReference = userRef.child("UserRegistration")
                        val selectedCountry = "United States, China, Nigeria, United Kingdom"
                        val userData = mapOf(
                            "uid" to userId,
                            "email" to email,
                            "gender" to selectedGender,
                            "country" to selectedCountry
                        )
                        //userRegistrationReference.child(userId).setValue(userData)
                    }

                    updateUI(auth.currentUser)
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
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            // Prompt the user to try again with different credentials
            Toast.makeText(this, "Please try again with different credentials.", Toast.LENGTH_SHORT).show()
        }
    }
}
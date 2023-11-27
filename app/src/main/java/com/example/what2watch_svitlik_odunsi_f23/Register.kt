package com.example.what2watch_svitlik_odunsi_f23
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val createUserButton = findViewById<Button>(R.id.Registerbutton)
        createUserButton.setOnClickListener {
            createUser()
        }

        button.setOnClickListener {
            val selectedCountry = spinnerCountry.selectedItem.toString()
            val selectedGender = spinnerGender.selectedItem.toString()

            // ... code to register the user with Firebase ...
        }

        val database = FirebaseDatabase.getInstance("https://your-firebase-database-url.firebaseio.com/")
        val myRef = database.getReference("users")

        data class User(
            val username: String,
            val email: String,
            val password: String,
            val country: String,
            val gender: String
        )

        button.setOnClickListener {
            val selectedCountry = spinnerCountry.selectedItem.toString()
            val selectedGender = spinnerGender.selectedItem.toString()

            val user = User(
                editUsername.text.toString(),
                editEmailAddress.text.toString(),
                editPassword.text.toString(),
                selectedCountry,
                selectedGender
            )

            myRef.child(user.username).setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add user!", Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun createUser() {
        val email = findViewById<EditText>(R.id.editEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editPassword).text.toString()
        val countries = arrayOf("USA", "UK", "India", "Canada", "Australia")
        val genders = arrayOf("Male", "Female", "Other")

        val countryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerCountry = null
        spinnerCountry.adapter = countryAdapter

        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerGender = null
        spinnerGender.adapter = genderAdapter

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        } else {
            Toast.makeText(baseContext, "Please enter your email and password.",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val uid = user.uid
            database.getReference("users/$uid").setValue(user.displayName)
            // ...
        } else {
            // ...
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

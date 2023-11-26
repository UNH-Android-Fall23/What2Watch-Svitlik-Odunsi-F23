package com.example.what2watch_svitlik_odunsi_f23
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
    }

    private fun createUser() {
        val email = findViewById<EditText>(R.id.editEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editPassword).text.toString()

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

package com.example.what2watch_svitlik_odunsi_f23

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Logout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        logout()
    }

    private fun logout() {
        // Logout from Firebase
        FirebaseAuth.getInstance().signOut()

        // Go back to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Close this activity
    }
}
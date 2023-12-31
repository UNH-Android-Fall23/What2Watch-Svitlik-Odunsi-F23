package com.example.what2watch_svitlik_odunsi_f23.ui.Profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.what2watch_svitlik_odunsi_f23.LoginActivity
import com.example.what2watch_svitlik_odunsi_f23.ProfileEdit
import com.example.what2watch_svitlik_odunsi_f23.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        val db = FirebaseFirestore.getInstance()
        db.collection("UserRegistration")
            .whereEqualTo("uid", currentUser?.uid)
            .get()
            .addOnSuccessListener { documents: QuerySnapshot ->
                for (document in documents) {
                    document.getString("fullName")?.let { fullName: String ->
                        view.findViewById<TextView>(R.id.FullName).text = fullName
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


       view.findViewById<TextView>(R.id.text_editProfile).setOnClickListener {
        }

        /*view.findViewById<TextView>(R.id.text_profile).setOnClickListener {

        }

        view.findViewById<TextView>(R.id.textView9).setOnClickListener {

        }*/
        view.findViewById<TextView>(R.id.textView11).setOnClickListener {

        }

        view.findViewById<TextView>(R.id.textView5).setOnClickListener {

        }

        view.findViewById<TextView>(R.id.text_Settings).setOnClickListener {
        }

        view.findViewById<TextView>(R.id.text_reviews).setOnClickListener {
        }

        view.findViewById<View>(R.id.logout_button).setOnClickListener {
            onLogoutClick(it)
        }

        view.findViewById<TextView>(R.id.text_editProfile).setOnClickListener {
            val intent = Intent(requireActivity(), ProfileEdit::class.java)
            startActivity(intent)
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Confirm Logout")
        builder.setMessage("Are you sure you want to log out?")
        builder.setPositiveButton("Yes") { _, _ ->
            logoutUser()
        }
        builder.setNegativeButton("No") { _, _ ->
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun onLogoutClick(view: View) {
        showLogoutDialog()
    }

    private fun logoutUser() {
        auth.signOut()

        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
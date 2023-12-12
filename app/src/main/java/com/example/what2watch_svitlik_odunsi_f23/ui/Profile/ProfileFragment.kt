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
import com.example.what2watch_svitlik_odunsi_f23.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FragmentProfile : Fragment() {

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
        db.collection("users")
            .whereEqualTo("uid", currentUser?.uid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    document.getString("username")?.let { username: String ->
                        view.findViewById<TextView>(R.id.username).text = username
                    }
                    document.getString("email")?.let { email: String ->
                        view.findViewById<TextView>(R.id.editEmailAddress).text = email
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }



        /*document?.let { doc ->
            doc.getString("username")?.let { username: String ->
                view.findViewById<TextView>(R.id.username).text = username
            }

            doc.getString("email")?.let { email: String ->
                view.findViewById<TextView>(R.id.editEmailAddress).text = email
            }

        }*/

        view.findViewById<TextView>(R.id.text_editProfile).setOnClickListener {
            // Navigate to the Edit Profile screen
        }

        view.findViewById<TextView>(R.id.text_Settings).setOnClickListener {
            // Navigate to the Settings screen
        }
        view.findViewById<TextView>(R.id.text_reviews).setOnClickListener {

        }



        view.findViewById<View>(R.id.logout_button).setOnClickListener {
            onLogoutClick(it)
        }

        //   view.findViewById<View>(R.id.text_reviews).setOnClickListener { Log.d(TAG) }
        // )
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
        // Calling the new function to show the logout dialog
        showLogoutDialog()
    }

    private fun logoutUser() {
        auth.signOut()

        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}
private fun Intent(profileFragment: FragmentProfile, java: Any): Intent {
    TODO("Not yet implemented")
}



package com.example.what2watch_svitlik_odunsi_f23.ui.Profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.what2watch_svitlik_odunsi_f23.LoginActivity
import com.example.what2watch_svitlik_odunsi_f23.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add a click listener to the logout button
        view.findViewById<View>(R.id.logout_button).setOnClickListener {
            onLogoutClick(it)
        }
    }

    private fun showLogoutDialog() {
        // Create a dialog to ask the user to confirm their logout
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Confirm Logout")
        builder.setMessage("Are you sure you want to log out?")
        builder.setPositiveButton("Yes") { _, _ ->
            // User confirmed logout, log out the user
            logoutUser()
        }
        builder.setNegativeButton("No") { _, _ ->
            // User canceled logout, do nothing
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun onLogoutClick(view: View) {
        // Call the new function to show the logout dialog
        showLogoutDialog()
    }

    private fun logoutUser() {
        // Perform your logout logic here

        // Navigate back to the login page
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
    private fun Intent(profileFragment: ProfileFragment, java: Any): Intent {
        TODO("Not yet implemented")
    }



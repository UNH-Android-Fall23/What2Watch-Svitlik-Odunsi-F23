package com.example.what2watch_svitlik_odunsi_f23
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_register, container, false)

        //initialize EditText and Button
        val editEmailAddress = view.findViewById<EditText>(R.id.editEmailAddress)
        val editPassword = view.findViewById<EditText>(R.id.editPassword)
        val registerButton = view.findViewById<Button>(R.id.Registerbutton)

        //handle register button click
        registerButton.setOnClickListener {
            //get the user's email and password
            val email = editEmailAddress.text.toString()
            val password = editPassword.text.toString()

            //validate the email and password
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            } else {
                //if the user's input is valid, navigate to fragment_home
                findNavController().navigate(R.id.action_nav_host_fragment_to_navigation_home)
            }
        }

        return view
    }
}
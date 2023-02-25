package com.example.tp_technomobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class ActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = view.findViewById<EditText>(R.id.editTextNumberPassword)

        val login = view.findViewById<Button>(R.id.button)
        login.setOnClickListener{
            if (email.text.isBlank() && password.text.isBlank()){
                Toast.makeText(context, "Email ou Mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_activityFragment_to_secondFragment2)
            }

        }
    }
}
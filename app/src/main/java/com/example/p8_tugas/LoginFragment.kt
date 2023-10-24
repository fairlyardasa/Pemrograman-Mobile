package com.example.p8_tugas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        val main = activity as MainActivity



        view.findViewById<Button>(R.id.btn_Login).setOnClickListener(){

            val tryUsername = view.findViewById<EditText>(R.id.edt_usernamelogin).text.toString()
            val tryPassword = view.findViewById<EditText>(R.id.edt_passwordlogin).text.toString()
            if ( tryUsername == MainActivity.USERNAME && tryPassword == MainActivity.PASSWORD) {

                val intentToDashboardActivity =
                    Intent( requireContext(), DashboardActivity::class.java).apply {

                }
                startActivity(intentToDashboardActivity)
            } else Toast.makeText(requireContext(), "Login Gagal!", Toast.LENGTH_SHORT).show()

        }

        return view
    }

}
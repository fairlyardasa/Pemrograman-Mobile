package com.example.p8_tugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Html
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        val main = activity as MainActivity

        val htmlString = "By checking the box you agree to our <a href=\"https://www.example.com/terms\"><b>Terms</b></a> and <a href=\"https://www.example.com/policy\"><b>Policy</b></a>."
        view.findViewById<CheckBox>(R.id.checkBox).text = Html.fromHtml(htmlString)

        val htmlStringLogin = "Already Have an Account? <a href=\"https://www.example.com/terms\"><b>Login</b></a>"
        view.findViewById<TextView>(R.id.txt_login).text = Html.fromHtml(htmlStringLogin)


        view.findViewById<AppCompatButton>(R.id.btn_Register).setOnClickListener(){
            Toast.makeText(requireContext(), "Register Berhasil", Toast.LENGTH_SHORT).show()
            val username = view.findViewById<EditText>(R.id.edt_username).text.toString()
            val password = view.findViewById<EditText>(R.id.edt_password).text.toString()

            main.viewPager.setCurrentItem(1)
            MainActivity.USERNAME = username
            MainActivity.PASSWORD = password
        }

        view.findViewById<TextView>(R.id.txt_login).setOnClickListener(){
            main.viewPager.setCurrentItem(1)
        }
        return view
    }
}
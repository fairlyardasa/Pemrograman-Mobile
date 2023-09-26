package com.example.tugaspertemuan5_loginpage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.text.HtmlCompat
import com.example.tugaspertemuan5_loginpage.MainActivity.Companion.EXTRA_EMAIL
import com.example.tugaspertemuan5_loginpage.MainActivity.Companion.EXTRA_PHONE
import com.example.tugaspertemuan5_loginpage.MainActivity.Companion.EXTRA_USERNAME
import com.example.tugaspertemuan5_loginpage.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uname = intent.getStringExtra(EXTRA_USERNAME)
        val phone = intent.getStringExtra(EXTRA_PHONE)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        with(binding){

            val unameText = "Welcome $uname"
            val emailText = "Your $email has been activated"
            val phoneText = "Your $phone has been registered"

            val spannableUname = SpannableString(unameText)
            spannableUname.setSpan(ForegroundColorSpan(Color.BLUE), 8, (uname.toString().length+8), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val spannableEmail = SpannableString(emailText)
            spannableEmail.setSpan(ForegroundColorSpan(Color.BLUE), 5, (email.toString().length + 5), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val spannablePhone = SpannableString(phoneText)
            spannablePhone.setSpan(ForegroundColorSpan(Color.BLUE), 5, (phone.toString().length+5), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            txtEmail.text = spannableEmail
            txtUsername.text = spannableUname
            txtPhone.text = spannablePhone

            btnLogout.setOnClickListener(){
                val intentToMainActivity =
                    Intent(this@SecondActivity, MainActivity::class.java)

                startActivity(intentToMainActivity)
                finish()
            }



        }
    }
}
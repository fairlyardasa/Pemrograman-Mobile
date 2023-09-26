package com.example.tugaspertemuan5_loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.tugaspertemuan5_loginpage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
            const val EXTRA_USERNAME = "com.example.tugaspertemuan5_loginpage.EXTRA_USERNAME"
            const val EXTRA_EMAIL = "com.example.tugaspertemuan5_loginpage.EXTRA_EMAIL"
            const val EXTRA_PHONE = "com.example.tugaspertemuan5_loginpage.EXTRA_PHONE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){

            val htmlString = "By checking the box you agree to our <a href=\"https://www.example.com/terms\"><b>Terms</b></a> and <a href=\"https://www.example.com/policy\"><b>Policy</b></a>."
            checkBox.text = Html.fromHtml(htmlString)

            val htmlStringLogin = "Already Have an Account? <a href=\"https://www.example.com/terms\"><b>Login</b></a>"
            txtLogin.text = Html.fromHtml(htmlStringLogin)

            btnRegister.setOnClickListener{
                val uname = edtUsername.text.toString()
                val email = edtEmail.text.toString()
                val phone = edtPhone.text.toString()

                val intentToSecondActivity =
                    Intent(this@MainActivity, SecondActivity::class.java).apply {
                        putExtra(EXTRA_USERNAME, uname)
                        putExtra(EXTRA_PHONE, phone)
                        putExtra(EXTRA_EMAIL, email)

                    }

                startActivity(intentToSecondActivity)
                finish()
            }

        }
    }
}
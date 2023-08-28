package com.example.pertemuandua
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pertemuandua.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var username = "user"
    private  var password = "pass123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){

            btnLogin.setOnClickListener {
                if (username == edtUsername.text.toString() && password == edtPassword.text.toString()) {
                    Toast.makeText(this@MainActivity, "Login Sukses", Toast.LENGTH_SHORT).show()

                } else Toast.makeText(this@MainActivity, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }


        }
    }
}
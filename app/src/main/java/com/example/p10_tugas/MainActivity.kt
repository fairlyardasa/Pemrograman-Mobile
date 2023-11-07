package com.example.p10_tugas

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p10_tugas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val adapterStudent = StudentAdapter(generateStudent()){
                student ->
                Toast.makeText(this@MainActivity,"Hei! you clicked an ${student.name} with ${student.ipk}" ,
                Toast.LENGTH_SHORT).show()
        }
        with(binding){
            recyclerView.apply {
                adapter = adapterStudent
                layoutManager = LinearLayoutManager(this@MainActivity)

            }
        }
    }

    fun generateStudent(): List<Student>{
        return listOf(
            Student( name = "Makudonaru", nim = "22/444888/SV/21333", ipk = 3.6 , prodi = "TEKNOLOGI REKAYASA PERANGKAT KERAS"),
            Student( name = "Mas Kobis", nim = "22/444888/SV/21333", ipk = 3.75 , prodi = "TEKNOLOGI REKAYASA INTERNET"),
            Student( name = "Tempo Gelato", nim = "22/444888/SV/21333", ipk = 3.4 , prodi = "TEKNOLOGI REKAYASA PERANGKAT LUNAK"),
            Student( name = "Sulis Mie Ayam", nim = "22/444888/SV/21333", ipk = 4.0 , prodi = "TEKNOLOGI REKAYASA PERANGKAT KERAS"),
            Student( name = "Adrian Sosis", nim = "22/444888/SV/21333", ipk = 1.6 , prodi = "TEKNOLOGI REKAYASA INTERNET"),

        )
    }
}
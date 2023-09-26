package com.example.tugaspertemuan6_presensiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tugaspertemuan6_presensiapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    var selectedDate = ""
    var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        with(binding) {
            val presensi = arrayOf(
                "Hadir tepat waktu",
                "Sakit",
                "Terlambat",
                "Izin"
            )

            val presensiAdapter =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, presensi)
            spinnerPresensi.adapter = presensiAdapter

            spinnerPresensi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedItem = spinnerPresensi.getItemAtPosition(p2).toString()

                    if (selectedItem != "Hadir tepat waktu") {
                        edtKeterangan.visibility = View.VISIBLE

                    } else edtKeterangan.visibility = View.INVISIBLE

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, monthOfYear, dayOfMonth)

                val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

                val monthName = monthFormat.format(calendar.time)

                selectedDate = "$dayOfMonth $monthName $year"
            }

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                val amPm = if (hourOfDay >= 12) "PM" else "AM"
                var formattedHour = 0
                if (hourOfDay >= 12) {
                    formattedHour = (hourOfDay.toInt() - 12)

                } else formattedHour = hourOfDay

                // Use the format() method to get the formatted time with AM/PM
                selectedTime = "$formattedHour:$minute $amPm"

            }

            btnSubmit.setOnClickListener(){
                val presensiTime = "Presensi berhasil $selectedDate jam $selectedTime"

                Toast.makeText(this@MainActivity,presensiTime, Toast.LENGTH_LONG).show()
            }

        }

    }
}
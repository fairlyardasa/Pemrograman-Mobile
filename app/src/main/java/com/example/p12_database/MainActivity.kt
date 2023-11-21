package com.example.p12_database

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p12_database.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var mNoteDao: NoteDao
    private lateinit var executorService: ExecutorService
    private var updateId : Int = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNoteDao = db!!.noteDao()!!

        with(binding){

            edtDate.setOnClickListener {
                val calendar = Calendar .getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(this@MainActivity, { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    edtDate.setText(selectedDate)
                }, year, month, day)

                datePickerDialog.show()
            }
            btnAdd.setOnClickListener{
                insert(Note(title = edtTitle.text.toString(), description = edtDesc.text.toString(), date = edtDate.text.toString() ))
                setEmptyField()
            }
            btnUpdate.setOnClickListener(){
                update(Note(
                    id = updateId, title = edtTitle.text.toString(), description = edtDesc.text.toString(), date = edtDate.text.toString()))
                updateId = 0
                setEmptyField()
            }

//            listView.setOnItemClickListener(){
//                    adapterView, _, i, _ ->
//                val item = adapterView.adapter.getItem(i) as Note
//                updateId = item.id
//                edtTitle.setText(item.title)
//                edtDesc.setText(item.description)
//            }

//            listView.onItemLongClickListener =
//                AdapterView.OnItemLongClickListener(){
//                    adapterView, view, i, l ->
//                    val item = adapterView.adapter.getItem(i) as Note
//                    delete(item)
//                    true
//
//            }

        }
    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    private fun getAllNotes(){
        mNoteDao.allNotes.observe(this){
            notes ->
            val adapter : ArrayAdapter<Note> =
            ArrayAdapter<Note>(
                this, android.R.layout.simple_list_item_1, notes
            )

            val adapterNotes = NoteAdapter(
                notes,
                onClickNote = { note ->
                    Toast.makeText(this@MainActivity, "Hey you clicked ${note.title}", Toast.LENGTH_SHORT).show()
                    updateId = note.id
                    binding.edtTitle.setText(note.title)
                    binding.edtDesc.setText(note.description)
                    binding.edtDate.setText(note.date)
                },
                mNotesDao = mNoteDao,
                executorService = executorService
            )

            binding.recyclerView.adapter = adapterNotes
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//            binding.listView.adapter = adapter
        }
    }

    private fun insert(note: Note){
        executorService.execute(){mNoteDao.insert(note)}
    }

    private fun delete(id: Int) {
        executorService.execute {
            mNoteDao.delete(id)
        }
    }

    private fun update(note: Note){
        executorService.execute(){mNoteDao.update(note)}
    }


    private fun setEmptyField(){
        with(binding){
            edtTitle.setText("")
            edtDesc.setText("")
            edtDate.setText("")
        }
    }


}


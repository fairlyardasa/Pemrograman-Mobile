package com.example.p13_tugas

import android.R
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.p13_tugas.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val firestore = FirebaseFirestore.getInstance()
    private val todoCollectionRef = firestore.collection("todo")
    private lateinit var binding : ActivityMainBinding
    private var updateId = ""
    private val todoListLiveData : MutableLiveData<List<Todo>> by lazy {
        MutableLiveData<List<Todo>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            btnAdd.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                val date = edtDate.text.toString()

                val newBudget = Todo(title = title, description = desc, date = date)
                addTodo(newBudget)
                setEmptyField()

            }

            btnUpdate.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                val date = edtDate.text.toString()

                val newBudget = Todo(title = title, description = desc, date = date)
                updateTodo(newBudget)
                updateId= ""
                setEmptyField()

            }

            listView.setOnItemClickListener {
                    adapterView, view, i , l ->
                val  item = adapterView.adapter.getItem(i) as Todo
                updateId = item.id
                edtDesc.setText(item.description)
                edtDate.setText(item.date)
                edtTitle.setText(item.title)
            }

            listView.onItemLongClickListener = AdapterView.OnItemLongClickListener{
                    adapterView, view, i, l ->
                val item = adapterView.adapter.getItem(i) as Todo
                deleteTodo(item)
                true
            }




        }

        observeBudgets()
        getAllTodos()
    }

    private fun getAllTodos(){
        observeTodoChanges();
    }

    private fun observeTodoChanges() {
        todoCollectionRef.addSnapshotListener{
                snapshots, error ->
            if ( error != null){
                Log.d("MainActivity", "Eror Listening for todo changes:", error)
            }
            val budgets = snapshots?.toObjects(Todo::class.java)
            if (budgets != null){
                todoListLiveData.postValue(budgets
                )
            }
        }
    }

    private fun observeBudgets(){
        todoListLiveData.observe(this){
                budgets ->
            val adapter = ArrayAdapter( this, R.layout.simple_list_item_1, budgets.toMutableList())
            val newAdapter = TodoAdapter(this, budgets)
            binding.listView.adapter = newAdapter
        }
    }

    private fun addTodo(todo: Todo){
        todoCollectionRef.add(todo).addOnSuccessListener {
                documentReference ->
            val createTodoId = documentReference.id
            todo.id = createTodoId
            documentReference.set(todo).addOnFailureListener{
                Log.d("MainActivity", "Error updating todo id :", it)
            }
        }.addOnFailureListener{
            Log.d("MainActivity", "Error updating todo id :", it)
        }
    }

    private fun updateTodo(todo: Todo){
        todo.id = updateId
        todoCollectionRef.document(updateId).set(todo).addOnFailureListener {
            Log.d("MainActivity", "error updating todo", it)
        }

    }

    private fun deleteTodo(todo: Todo){
        if (todo.id.isEmpty()){
            Log.d("MainActivity", "Error updating budget")
            return
        }

        todoCollectionRef.document(todo.id).delete().addOnFailureListener {
            Log.d("MainActivity", "Eror deleting budget", it)
        }
    }

    private fun setEmptyField(){
        with(binding){
            edtTitle.setText("")
            edtDate.setText("")
            edtDesc.setText("")
        }
    }


}
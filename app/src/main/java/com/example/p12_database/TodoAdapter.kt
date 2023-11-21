package com.example.p12_database

import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.p12_database.databinding.ItemNoteBinding

typealias onClickTodo = (Todo)-> Unit

class TodoAdapter (private val listTodo : List<Todo>?,
                   private val onClickTodo : onClickTodo) : RecyclerView.Adapter<TodoAdapter.ItemTodoViewHolder>() {

    inner class ItemTodoViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (data:Todo){
            with(binding){

                txtTitle.text = data.title
                txtAuthor.text = data.description
                txtTime.text = data.date

                itemView.setOnClickListener{
                    onClickTodo(data)
                }


            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTodoViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemTodoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}
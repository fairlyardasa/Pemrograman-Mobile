package com.example.p12_database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.p12_database.databinding.ActivityMainBinding
import com.example.p12_database.databinding.ItemNoteBinding
import java.util.concurrent.ExecutorService


typealias onClickNote = (Note)-> Unit

class NoteAdapter (private val listNotes: List<Note>,
                   private val onClickNote : onClickNote,
                   private val mNotesDao: NoteDao,
                   private val executorService: ExecutorService
) : RecyclerView.Adapter<NoteAdapter.ItemNotesViewHolder>(){

    inner class ItemNotesViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Note) {

            val id = data.id
            with(binding) {

                txtTitle.text = data.title
                txtAuthor.text = data.description
                txtTime.text = data.date

                itemView.setOnClickListener {
                    onClickNote(data)
                }

                itemView.setOnLongClickListener {
                    delete(id)
                    true
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ItemNotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNotes?.size?:0
    }

    override fun onBindViewHolder(holder: ItemNotesViewHolder, position: Int) {
        listNotes?.get(position)?.let{holder.bind(it)}
    }

    private fun delete(id: Int) {
        executorService.execute {
            mNotesDao.delete(id)
        }

    }

    fun getList(): List<Note> {
        return listNotes
    }

}
package com.example.p10_tugas

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.p10_tugas.databinding.ItemStudentBinding

typealias onClickStudent = (Student) -> Unit

class StudentAdapter(private val listStudent: List<Student>, private val onClickStudent: onClickStudent): RecyclerView.Adapter<StudentAdapter.ItemStudentViewHolder>() {

    inner class ItemStudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:Student){
            with(binding){
                txtNama.text = data.name
                txtNim.text = data.nim
                txtIpk.text = "IPK " + data.ipk.toString()
                txtProdi.text = data.prodi

                if ( data.ipk < 3.5) {
                    txtIpk.setBackgroundResource(R.drawable.border_box_danger)
                    txtIpk.setTextColor(Color.parseColor("#FF0000"))
                }


                itemView.setOnClickListener{
                    onClickStudent(data)
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.ItemStudentViewHolder {
    val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ItemStudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ItemStudentViewHolder, position: Int) {
        holder.bind(listStudent[position])
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }
}
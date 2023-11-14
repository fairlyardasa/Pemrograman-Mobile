package com.example.p11_retrovidapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.p11_retrovidapp.databinding.ItemNewsBinding
import com.example.p11_retrovidapp.model.News

typealias onClickNews = (News)-> Unit

class NewsAdapter(private val listNews: List<News>?, private val onClickNews: onClickNews):RecyclerView.Adapter<NewsAdapter.ItemNewsViewHolder>() {

    inner class ItemNewsViewHolder(private val binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data:News){
        with(binding){

            txtTitle.text = data.title.toString()
            txtAuthor.text= "Author : " + data.author.toString()
            txtTime.text = data .time.toString()

            itemView.setOnClickListener{
                onClickNews(data)
            }

            }
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAdapter.ItemNewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ItemNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ItemNewsViewHolder, position: Int) {
        listNews?.get(position)?.let{holder.bind(it)}
    }

    override fun getItemCount(): Int {
        return listNews?.size?:0
    }
}
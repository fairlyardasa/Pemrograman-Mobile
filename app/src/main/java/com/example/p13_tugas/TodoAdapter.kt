package com.example.p13_tugas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TodoAdapter(context: Context, items: List<Todo>) : ArrayAdapter<Todo>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView
        val holder: ViewHolder

        if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
            holder = ViewHolder()
            holder.titleTextView = rowView.findViewById(R.id.txt_title)
            holder.authorTextView = rowView.findViewById(R.id.txt_author)
            holder.timeTextView = rowView.findViewById(R.id.txt_time)
            rowView.tag = holder
        } else {
            holder = rowView.tag as ViewHolder
        }

        val item = getItem(position)
        holder.titleTextView?.text = item?.title ?: ""
        holder.authorTextView?.text = item?.description ?: ""
        holder.timeTextView?.text = item?.date ?: ""

        return rowView!!
    }

    private class ViewHolder {
        var titleTextView: TextView? = null
        var authorTextView: TextView? = null
        var timeTextView: TextView? = null
    }
}

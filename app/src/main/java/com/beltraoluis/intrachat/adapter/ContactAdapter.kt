package com.beltraoluis.intrachat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.viewHolder.ContactViewHolder
import java.sql.Timestamp

class ContactAdapter(data: MutableList<Pair<Timestamp,String>>?): RecyclerView.Adapter<ContactViewHolder>() {

    private val dataSet = data ?: mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact, parent, false)

        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.set(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun add(p: Pair<Timestamp,String>){
        dataSet.add(p)
        notifyDataSetChanged()
    }
}
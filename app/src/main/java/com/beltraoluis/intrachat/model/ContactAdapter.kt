package com.beltraoluis.intrachat.model

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.beltraoluis.intrachat.R

class ContactAdapter(data: MutableList<String>): RecyclerView.Adapter<ContactViewHolder>() {

    private val dataSet = data

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
}
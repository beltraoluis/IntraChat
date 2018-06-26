package com.beltraoluis.intrachat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.viewHolder.GraphViewHolder
import java.sql.Timestamp

class GraphAdapter(data: Pair<String,String>): RecyclerView.Adapter<GraphViewHolder>() {

    private val dataSet =mutableListOf(data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.graph, parent, false)

        return GraphViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GraphViewHolder, position: Int) {
        holder.set(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
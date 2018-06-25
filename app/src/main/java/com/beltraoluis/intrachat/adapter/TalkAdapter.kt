package com.beltraoluis.intrachat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.viewHolder.TalkViewHolder

class TalkAdapter(): RecyclerView.Adapter<TalkViewHolder>() {

    var dataSet = Control.conversation[Control.activeIp]!!.messages

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalkViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.talk_item, parent, false)

        return TalkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TalkViewHolder, position: Int) {
        holder.set(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun update(){
        dataSet = Control.conversation[Control.activeIp]!!.messages
        notifyDataSetChanged()
    }
}
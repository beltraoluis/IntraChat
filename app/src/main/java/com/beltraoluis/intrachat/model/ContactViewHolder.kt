package com.beltraoluis.intrachat.model

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.contact.view.*


class ContactViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
    val ip = itemView?.contact_ip

    fun set(s: String){
        ip?.text = s
    }
}
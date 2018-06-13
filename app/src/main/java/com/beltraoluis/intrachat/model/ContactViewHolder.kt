package com.beltraoluis.intrachat.model

import android.support.v7.widget.RecyclerView
import android.view.View
import com.beltraoluis.intrachat.Control
import kotlinx.android.synthetic.main.contact.view.*
import org.jetbrains.anko.longToast
import java.sql.Timestamp


class ContactViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
    val ip = itemView?.contact_ip
    val time = itemView?.contact_time

    fun set(c: Pair<Timestamp,String>){
        ip?.text = c.second
        time?.text = c.first.toString()
        itemView?.setOnClickListener {
            Control.mainContext?.longToast(ip?.text ?: "")?.show()
        }
    }
}
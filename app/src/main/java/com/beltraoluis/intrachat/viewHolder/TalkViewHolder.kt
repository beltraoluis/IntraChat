package com.beltraoluis.intrachat.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.model.Message
import kotlinx.android.synthetic.main.talk_item.view.*
import java.sql.Timestamp

class TalkViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView){
    val time = itemView?.talkItem_time
    val message = itemView?.talkItem_message
    val ip = itemView?.talkItem_ip
    val card = itemView?.talkItem_card
    val light_green = itemView?.context?.getColor(R.color.light_green) ?: 0

    fun set(m: Message){
        m.decode()
        if(m.ip.equals(Control.myIp)){
            card?.setCardBackgroundColor(light_green)
        }
        time?.text = Timestamp(m.time).toString()
        message?.text = m.message
        ip?.text = m.ip
        m.encode()
    }
}
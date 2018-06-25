package com.beltraoluis.intrachat.model

import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.connection.LineCode.NRZ
import com.beltraoluis.intrachat.connection.LineCode.RZ
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON

@Serializable
data class Message(
        var lineCode: Int,
        var ip: String,
        var message: String,
        var time: Long
){
    companion object {
        fun toMessage(s: String): Message{
            return JSON.parse<Message>(s)
        }
    }

    fun toJson(): String{
        when(lineCode){
            Control.NRZ_CODE -> {
                val lc = NRZ()
                message = lc.encode(message)
            }
            Control.RZ_CODE -> {
                val lc = RZ()
                message = lc.encode(message)
            }
        }
        return JSON.stringify(this)
    }
}
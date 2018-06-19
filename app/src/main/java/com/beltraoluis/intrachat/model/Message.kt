package com.beltraoluis.intrachat.model

import com.beltraoluis.intrachat.connection.LineCode.NRZ
import com.beltraoluis.intrachat.connection.LineCode.RZ
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON

@Serializable
data class Message(
        var lineCode: String,
        var message: String,
        var time: Long
){
    companion object {
        val NRZ_CODE = "NRZ"
        val RZ_CODE = "RZ"

        fun toMessage(s: String): Message{
            return JSON.parse<Message>(s)
        }
    }

    fun toJson(): String{
        when(lineCode){
            NRZ_CODE -> {
                val lc = NRZ()
                message = lc.encode(message)
            }
            RZ_CODE -> {
                val lc = RZ()
                message = lc.encode(message)
            }
        }
        return JSON.stringify(this)
    }
}
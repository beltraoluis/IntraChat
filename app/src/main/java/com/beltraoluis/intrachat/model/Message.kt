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
    @Optional
    var encoded = false
    companion object {
        fun toMessage(s: String): Message{
            return JSON.parse<Message>(s)
        }
    }

    fun toJson(): String{
        encode()
        return JSON.stringify(this)
    }

    fun encode(){
        if(!encoded) {
            encoded = true
            when (lineCode) {
                Control.NRZ_CODE -> {
                    val lc = NRZ()
                    message = lc.encode(message)
                }
                Control.RZ_CODE -> {
                    val lc = RZ()
                    message = lc.encode(message)
                }
            }
        }
    }

    fun decode(){
        if(encoded) {
            encoded = false
            when (lineCode) {
                Control.NRZ_CODE -> {
                    val lc = NRZ()
                    message = lc.decode(message)
                }
                Control.RZ_CODE -> {
                    val lc = RZ()
                    message = lc.decode(message)
                }
            }
        }
    }

    override fun toString(): String{
        return JSON.stringify(this)
    }
}
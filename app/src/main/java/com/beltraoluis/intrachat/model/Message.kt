package com.beltraoluis.intrachat.model

data class Message(
        var lineCode: String,
        var message: String,
        var time: Long
){
    companion object {
        val NRZ_CODE = "NRZ"
        val RZ_CODE = "RZ"
    }
}
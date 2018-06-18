package com.beltraoluis.intrachat.model

import java.sql.Timestamp

data class Conversation (
        var time: Long,
        var messages:MutableList<Message> = mutableListOf()
)
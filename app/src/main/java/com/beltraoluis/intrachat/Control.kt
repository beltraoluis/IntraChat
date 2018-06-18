package com.beltraoluis.intrachat

import com.beltraoluis.intrachat.activity.MainActivity
import com.beltraoluis.intrachat.model.Conversation

object Control {
    var main: MainActivity? = null
    var state: String? = null
    var activeIp: String? = null
    var conversation = mutableMapOf<String, Conversation>()
}
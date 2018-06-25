package com.beltraoluis.intrachat

import com.beltraoluis.intrachat.activity.MainActivity
import com.beltraoluis.intrachat.fragment.IntraChatFragment
import com.beltraoluis.intrachat.model.Conversation

object Control {
    val NRZ_CODE = 0
    val RZ_CODE = 1
    val DOOR = 5000

    var main: MainActivity? = null
    var state: String? = null
    var activeIp: String? = null
    var activeFragment: IntraChatFragment? = null
    var myIp: String? = null
    var conversation = mutableMapOf<String, Conversation>()
    var codeline_code = 0
}
package com.beltraoluis.intrachat.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import kotlinx.android.synthetic.main.fragment_talk.*

class TalkFragment : Fragment() {

    var ip: String? = null
    lateinit var textIP: TextView

    companion object {
        fun newInstance(ip: String): TalkFragment {
            val f = TalkFragment()
            val args = Bundle()
            args.putString("ip", ip)
            f.arguments = args
            return f
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ip = savedInstanceState?.getString("ip")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_talk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textIP = talk_ip
        textIP.text = Control.activeIp ?: ""
    }
}

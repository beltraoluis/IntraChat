package com.beltraoluis.intrachat.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import kotlinx.android.synthetic.main.fragment_talk.*
import com.beltraoluis.intrachat.model.Message
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.io.ObjectOutputStream
import java.net.Socket


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
        sendButton()
    }

    fun sendButton(){
        val send = talk_send
        val message = talk_message
        send.setOnClickListener{
            val line = Message(
                    lineCode = Control.codeline_code,
                    ip = Control.myIp ?: "",
                    message = message.text.toString(),
                    time = System.currentTimeMillis()
            )
            val json = line.toJson()
            doAsync {
                try {
                    val client = Socket(Control.activeIp, Control.DOOR)
                    val dataOut = ObjectOutputStream(client.getOutputStream())
                    dataOut.flush()
                    dataOut.writeObject(json)
                    dataOut.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    Control.main!!.update(Control.activeIp!!,json)
                    uiThread {
                        message.text.clear()
                    }
                }
            }
        }
    }
}

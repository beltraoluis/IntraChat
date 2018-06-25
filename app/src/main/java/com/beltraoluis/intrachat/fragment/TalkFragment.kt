package com.beltraoluis.intrachat.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.adapter.ContactAdapter
import com.beltraoluis.intrachat.adapter.TalkAdapter
import kotlinx.android.synthetic.main.fragment_talk.*
import com.beltraoluis.intrachat.model.Message
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import java.io.IOException
import java.io.ObjectOutputStream
import java.net.Socket


class TalkFragment : Fragment(), IntraChatFragment {
    lateinit var recycler: RecyclerView
    lateinit var recyclerAdapter: TalkAdapter
    var loop = true

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
        Control.activeFragment = this
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
        recyclerAdapter = TalkAdapter()
        recycler = talk_conversation.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
    }

//    override fun onAttach(context: Context?){
//        super.onAttach(context)
//        loop = true
//        doAsync {
//            if(loop){
//                Thread.sleep(300L)
//                recycler.adapter.notifyDataSetChanged()
//            }
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        loop = false
//    }

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
                        message.clearFocus()
                        activity?.inputMethodManager
                                ?.hideSoftInputFromWindow(view?.windowToken,0)
                    }
                }
            }
        }
    }

    override fun updateRecycler() {
        Control.main?.runOnUiThread {
            recycler.adapter.notifyDataSetChanged()
        }
    }
}

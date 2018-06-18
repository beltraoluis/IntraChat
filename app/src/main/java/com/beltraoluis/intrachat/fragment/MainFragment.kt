package com.beltraoluis.intrachat.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.text.format.Formatter
import android.widget.EditText
import android.widget.TextView
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.model.ContactAdapter
import com.beltraoluis.intrachat.model.Conversation
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.customView
import org.jetbrains.anko.dip
import org.jetbrains.anko.editText
import org.jetbrains.anko.padding
import org.jetbrains.anko.support.v4.alert
import java.sql.Timestamp


/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : Fragment() {

    lateinit var ip: TextView
    lateinit var fab: FloatingActionButton
    lateinit var recycler: RecyclerView
    lateinit var recyclerAdapter: ContactAdapter

    companion object {
        fun newInstance(ip: String): MainFragment {
            val f = MainFragment()
            val args = Bundle()
            args.putString("index", ip)
            f.arguments = args
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ipAddress = getDeviceIpWifiData()
        ip = main_ip
        fab = mainFragment_fab
        ip.text = ipAddress
        recyclerAdapter = ContactAdapter(null)
        recycler = main_recycler.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
        if(Control.conversation.isNotEmpty()){
            Control.conversation.forEach {
                recyclerAdapter.add(Timestamp(it.value.time) to it.key)
            }
        }
        fab.setOnClickListener {
            lateinit var ip: EditText
            alert("", "Iniciar Conversa:") {
                customView {
                    ip = editText {
                        padding = dip(8)
                        hint = "endereço IP"
                        inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL

                    }
                }
                positiveButton("Enviar") {
                    Control.conversation[ip.text.toString()] = Conversation(System.currentTimeMillis())
                    recyclerAdapter.add(Timestamp(System.currentTimeMillis()) to ip.text.toString())
                }
                negativeButton("Voltar") {}
            }.show()
        }
    }

    fun getDeviceIpWifiData(): String {
        val wm = activity?.applicationContext?.getSystemService(WIFI_SERVICE) as WifiManager
        @SuppressWarnings("deprecation")
        val ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress())
        return ip
    }
}

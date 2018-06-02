package com.beltraoluis.intrachat

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.Formatter
import android.widget.TextView
import com.beltraoluis.intrachat.model.ContactAdapter
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    lateinit var ip: TextView
    lateinit var recycler: RecyclerView
    lateinit var recyclerAdapter: ContactAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ipAddress = getDeviceIpWifiData()
        ip = main_ip
        ip.text = ipAddress
        recyclerAdapter = ContactAdapter(mutableListOf("192.168.1.101",
                "192.168.1.102",
                "192.168.1.103",
                "192.168.1.104",
                "192.168.1.105",
                "192.168.1.106",
                "192.168.1.107",
                "192.168.1.108",
                "192.168.1.109",
                "192.168.1.110",
                "192.168.1.111"))
        recycler = main_recycler.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
    }

    fun getDeviceIpWifiData(): String {
        val wm = activity?.applicationContext?.getSystemService(WIFI_SERVICE) as WifiManager
        @SuppressWarnings("deprecation")
        val ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress())
        return ip
    }
}

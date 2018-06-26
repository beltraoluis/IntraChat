package com.beltraoluis.intrachat.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.connection.TcpServer
import com.beltraoluis.intrachat.fragment.MainFragment
import com.beltraoluis.intrachat.fragment.TalkFragment
import com.beltraoluis.intrachat.model.Conversation
import com.beltraoluis.intrachat.model.Message
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_FRAGMENT = "main"
        const val TALK_FRAGMENT = "talk"
    }

    lateinit var server: TcpServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Control.main = this
        callFragment("main","")
        server = TcpServer(this,Control.DOOR)
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stopServer()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.lineCode_menu -> {
                alert{
                    customView{
                        verticalLayout{
                            padding = dip(8)
                            textView("Códigos de linha"){
                                textSize = 20F
                            }
                            radioGroup {
                                val nrz = radioButton{
                                    text = "NRZ"
                                }
                                val rz = radioButton{
                                    text = "RZ"
                                }
                                when(Control.codeline_code){
                                    Control.NRZ_CODE -> {
                                        nrz.isChecked = true
                                        rz.isChecked = false
                                    }
                                    Control.RZ_CODE -> {
                                        rz.isChecked = true
                                        nrz.isChecked = false
                                    }
                                }
                                nrz.setOnClickListener{
                                    nrz.isChecked = true
                                    rz.isChecked = false
                                    Control.codeline_code = Control.NRZ_CODE
                                }
                                rz.setOnClickListener{
                                    rz.isChecked = true
                                    nrz.isChecked = false
                                    Control.codeline_code = Control.RZ_CODE
                                }
                            }
                        }
                    }
                }.show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun update(ip: String, message: String){
        if(Control.conversation[ip] == null){
            Control.conversation[ip] = Conversation(System.currentTimeMillis())
            runOnUiThread {
                if(Control.state.equals(MAIN_FRAGMENT)){
                    val frag = Control.activeFragment as MainFragment
                    frag.recyclerAdapter.add(Timestamp(Control.conversation[ip]!!.time) to ip)
                }
            }
        }
        Control.conversation[ip]!!.messages.add(Message.toMessage(message))
        Log.i("Message",message)
        Control.activeFragment?.updateRecycler()
    }

    fun callFragment(s: String, ip: String){
        var frag = supportFragmentManager.findFragmentByTag(s)
        when(s){
            MAIN_FRAGMENT -> {
                if(frag == null){
                    frag = MainFragment.newInstance(ip)
                    supportFragmentManager
                            .beginTransaction()
                            .add(R.id.frame,frag,MAIN_FRAGMENT)
                            .commit()
                }
                Control.state = MAIN_FRAGMENT
            }
            TALK_FRAGMENT -> {
                if(frag == null){
                    frag = TalkFragment.newInstance(ip)
                    supportFragmentManager
                            .beginTransaction()
                            .add(R.id.frame,frag,TALK_FRAGMENT)
                            .commit()
                }
                Control.state = TALK_FRAGMENT
            }
            else -> {}
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame,frag)
                .commit()
    }

    override fun finish() {
        if(Control.state.equals(MAIN_FRAGMENT)){
            alert("Deseja realmente sair do aplicativo?","sair") {
                yesButton { super.finish() }
                noButton { }
            }.show()
        }
        else{
            callFragment(MAIN_FRAGMENT,"")
        }
    }
}

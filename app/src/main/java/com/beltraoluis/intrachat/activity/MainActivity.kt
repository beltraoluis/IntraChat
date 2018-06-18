package com.beltraoluis.intrachat.activity

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.beltraoluis.intrachat.Control
import com.beltraoluis.intrachat.R
import com.beltraoluis.intrachat.fragment.MainFragment
import com.beltraoluis.intrachat.fragment.TalkFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_FRAGMENT = "main"
        const val TALK_FRAGMENT = "talk"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Control.main = this
        callFragment("main","")

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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun update(ip: String, message: String){

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
            super.finish()
        }
        else{
            callFragment(MAIN_FRAGMENT,"")
        }
    }
}

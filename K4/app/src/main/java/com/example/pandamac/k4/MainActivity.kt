package com.example.pandamac.k4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        invis_bt.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> help_message.visibility = INVISIBLE
                false -> help_message.visibility = VISIBLE
            }
        }


        change_background.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> help_message.setBackgroundResource(R.color.colorPrimary)
                false -> help_message.setBackgroundResource(R.color.white)
            }

        }

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
            R.id.help_menu -> displayHelpMessage()
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun displayHelpMessage(): Boolean {
        help_message.setText(R.string.help_change)
        return true

    }

}

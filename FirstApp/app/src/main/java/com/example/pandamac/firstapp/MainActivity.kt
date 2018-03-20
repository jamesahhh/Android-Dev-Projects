package com.example.pandamac.firstapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt1 = findViewById<Button>(R.id.button)
        bt1.setOnClickListener {
            Toast.makeText(applicationContext, "This is my first app", Toast.LENGTH_LONG).show()
        }



    }
}

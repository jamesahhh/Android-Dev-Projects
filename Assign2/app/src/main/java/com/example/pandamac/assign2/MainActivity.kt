package com.example.pandamac.assign2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.username)
        val nameString = input.text
        val createButton = findViewById<Button>(R.id.create)
        val radioGroup = findViewById<RadioGroup>(R.id.gender)

        radioGroup?.setOnCheckedChangeListener { _,checkedId ->
            val rbMale = findViewById<RadioButton>(R.id.male)
            val rbFemale = findViewById<RadioButton>(R.id.female)
            var genderText = ""
            when (checkedId) {
                R.id.male -> {
                    rbMale.alpha = 0.2f
                    rbFemale.alpha = 1.0f
                    genderText = "male"
                }
                R.id.female -> {
                    rbMale.alpha = 1.0f
                    rbFemale.alpha = 0.2f
                    genderText = "female"
                }
            }

            createButton.setOnClickListener {
                Toast.makeText(this, "User $nameString was created, gender: $genderText", Toast.LENGTH_SHORT).show()
            }
        }


    }

}



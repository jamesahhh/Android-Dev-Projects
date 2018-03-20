package com.example.pandamac.colorpicker
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.*


class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, View.OnClickListener{


    private val MAX = 255
    private var red = 0
    private var green = 0
    private var blue = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.dickbutt)

        redBar.max = MAX
        blueBar.max = MAX
        greenBar.max = MAX

        redBar.setOnSeekBarChangeListener(this)
        blueBar.setOnSeekBarChangeListener(this)
        greenBar.setOnSeekBarChangeListener(this)

        val info = intent.extras
        if(info != null){
            if(info.containsKey("app_key")){
                returnButton.visibility = VISIBLE
                returnButton.setOnClickListener(this)
            }
        }
    }

    override fun onClick(p0: View?) {
        returnButton.visibility = INVISIBLE
        finish()

    }



    override fun finish(){
        intent.putExtra("red", red)
        intent.putExtra("blue", blue)
        intent.putExtra("green", green)
        setResult(RESULT_OK, intent)
        super.finish()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean){

        when(seekBar.id) {

            R.id.redBar -> {
                red = progress
                progressR.text= red.toString()
            }
            R.id.blueBar -> {
                blue = progress
                progressB.text= blue.toString()
            }
            R.id.greenBar -> {
                green = progress
                progressG.text =  green.toString()
            }
        }

        colorDisplay.setBackgroundColor(Color.rgb(red,green,blue))

    }


    override fun onStartTrackingTouch(seekBar: SeekBar) {
        textView.setText(R.string.changing_color)
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        textView.setText(R.string.color_picker)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.save -> {
                return true
            }
            R.id.display_colors -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}





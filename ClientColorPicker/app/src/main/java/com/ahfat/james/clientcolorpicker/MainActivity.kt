package com.ahfat.james.clientcolorpicker

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private var red = IntArray(3)
    private var blue = IntArray(3)
    private var green = IntArray(3)

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.color1 -> displayColorPicker(1)
            R.id.color2 -> displayColorPicker(2)
            R.id.blender -> displayBlended()
        }

    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        displayBlended()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        Toast.makeText(applicationContext, "Blending... WoW", Toast.LENGTH_SHORT).show()
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        Toast.makeText(applicationContext, "Current RGB\n" +
                                                "red: ${red[0]}\n" +
                                                "green: ${green[0]}\n" +
                                                "blue: ${blue[0]}", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        color1.setOnClickListener(this)
        color2.setOnClickListener(this)
        blender.setOnClickListener(this)
        seekBar.setOnSeekBarChangeListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && (requestCode == 1 || requestCode == 2)){
            red[requestCode] = data!!.getIntExtra("red", 0)
            green[requestCode] = data!!. getIntExtra ("green", 0)
            blue[requestCode] = data!!.getIntExtra("blue", 0)
            setColor(red[requestCode], green[requestCode], blue[requestCode], requestCode)

        }
    }

    private fun setColor(red: Int, green: Int, blue: Int, requestCode: Int){
        var view: View? = null
        when(requestCode){
            1 -> {
                view = findViewById(R.id.firstColorView)
            }
            2 -> {
                view = findViewById(R.id.secondColorView)
            }
        }
        view!!.setBackgroundColor(Color.rgb(red,green,blue))
    }

    private fun displayColorPicker(requestCode: Int){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.putExtra("app_key", 0)
        startActivityForResult(intent, requestCode)
    }

    private fun displayBlended(){
        val rightpercent  = this.seekBar.progress.toFloat()/100
        val leftpercent = 1 - rightpercent
        red[0] = ((red[1].toFloat()* leftpercent) + (red[2].toFloat()* rightpercent)).toInt()
        green[0] = ((green[1].toFloat()* leftpercent) + (green[2].toFloat()* rightpercent)).toInt()
        blue[0] = ((blue[1].toFloat()* leftpercent) + (blue[2].toFloat()* rightpercent)).toInt()
        val blendedColor = blendedView
        blendedColor.setBackgroundColor(Color.rgb(red[0],green[0], blue[0]))
    }



}

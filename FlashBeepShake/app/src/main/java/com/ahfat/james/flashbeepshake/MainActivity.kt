package com.ahfat.james.flashbeepshake

import android.content.Context
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private var camManager: CameraManager? = null
    private var vibrator: Vibrator? = null
    private var phoneCam: String? = null
    private var tone: ToneGenerator? = null
    private var viber: VibrationEffect? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        phoneCam = camManager!!.cameraIdList[0]

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        lightSwitch.setOnCheckedChangeListener(this)

        vibratorkappa.setOnSeekBarChangeListener(this)

        tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

    }

    override fun onCheckedChanged(button: CompoundButton?, bool: Boolean) {
        when(button){
            lightSwitch -> {
                if(bool){
                    camManager!!.setTorchMode(phoneCam, bool)

                }else camManager!!.setTorchMode(phoneCam, bool)
            }
        }
    }

    override fun onClick(button: View?) {
        when(button){
            beep -> tone!!.startTone(ToneGenerator.TONE_DTMF_4, 600)
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProgressChanged(bar: SeekBar?, progress: Int, bool: Boolean) {
        when(bar){
            vibratorkappa -> {
                viber = VibrationEffect.createOneShot(2000, progress)
                vibrator!!.vibrate(viber)
            }
        }
    }
}

package com.ahfat.james.flashbeepshake

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.media.ToneGenerator.MAX_VOLUME
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener, View.OnClickListener {G
    private var connectUUID: UUID? = null
    private var camManager: CameraManager? = null
    private var vibrator: Vibrator? = null
    private var phoneCam: String? = null
    private var tone: ToneGenerator? = null
    private var viber: VibrationEffect? = null
    private var myBluetoothAdapter: BluetoothAdapter? = null
    private var server: AcceptThread? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectUUID = UUID.fromString(connectGUID)

        camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        phoneCam = camManager!!.cameraIdList[0]

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        lightSwitch.setOnCheckedChangeListener(this)

        vibratorkappa.setOnClickListener(this)
        beep.setOnClickListener(this)
        tone = ToneGenerator(AudioManager.STREAM_MUSIC, 50)

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
        when(button) {
            beep -> tone!!.startTone(ToneGenerator.TONE_DTMF_3, 1000)
            vibratorkappa -> {
                viber = VibrationEffect.createOneShot(200, 30 )
                vibrator!!.vibrate(viber)
            }
        }
    }

    private inner class AcceptThread: Thread(){

        private var serverSocket: BluetoothServerSocket? = null
        init {
            val tmp: BluetoothServerSocket
            try{
                tmp = myBluetoothAdapter!!.listenUsingRfcommWithServiceRecord(SERVICE_NAME, connectUUID)
                serverSocket = tmp
                Log.e(SERVER, "Connection Successful")
            }
            catch(e: IOException){
                Log.e(SERVER, "Failed to Recieve")
            }
        }

        override fun run(){
            var socket: BluetoothSocket?
            while(true) {
                Log.i(SERVER, "AcceptThread.run()")
                try{
                    socket = serverSocket!!.accept()
                    Log.i(SERVER,"Accepting connection")
                } catch (e: IOException){
                    Log.e(SERVER, "Accept Socket Exception")
                    break
                }

                if(socket != null) {
                    Log.i(SERVER, "Server Connection accepted")

                }
            }
        }

    }
    companion object {
        private const val SERVER = "Server"
        private const val SERVICE_NAME = "Flasher"
        private const val connectGUID = "0ef46472-473b-11e8-842f-0ed5f89f718b"
    }
}

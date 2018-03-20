package com.ebookfrenzy.fragmentexample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.content.Context
import kotlinx.android.synthetic.main.toolbar_fragment.*

/**
 * Created by pandamac on 2/20/18.
 */
class ToolbarFragment: Fragment(), SeekBar.OnSeekBarChangeListener {

    var seekvalue = 10
    var activityCallBack: ToolbarFragment.ToolbarListener? = null

    interface ToolbarListener{
        fun onButtonClick(position: Int, text: String)
    }

    override fun onAttach(context: Context?){
        super.onAttach(context)
        try{
            activityCallBack = context as ToolbarListener
        }catch(e: ClassCastException){
            throw ClassCastException(context?.toString() + "must implement ToolbarListener")
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.toolbar_fragment, container, false)
        val seekbar:SeekBar? = view?.findViewById(R.id.seekBar1)
        val button:Button? = view?.findViewById(R.id.button1)
        seekbar?.setOnSeekBarChangeListener(this)
        button?.setOnClickListener{
            v:View -> buttonClicked(v)}
        return view
    }

    private fun buttonClicked(view: View){
        activityCallBack?.onButtonClick(seekvalue, editText1.text.toString())
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        seekvalue = progress
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}
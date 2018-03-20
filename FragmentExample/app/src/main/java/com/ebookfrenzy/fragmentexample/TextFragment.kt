package com.ebookfrenzy.fragmentexample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.text_fragment.*

/**
 * Created by pandamac on 2/20/18.
 */
class TextFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.text_fragment,container,false)
    }

    fun changeTextProperties(fontSize: Int, text: String){
        textView1.textSize = fontSize.toFloat()
        textView1.text = text

    }
}
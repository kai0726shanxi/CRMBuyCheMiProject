package com.buychemi.crm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import com.buychemi.crm.R

/**
 * @Author 20342
 * @Date 2019/8/14 15:54
 */
class ChoseFollowTypeDialog : Dialog, View.OnClickListener {


    private var mRadioGroup: RadioGroup? = null
    private var mOnclick: BtnDataLinsenter? = null
    private var mRadioButton1: RadioButton? = null
    private var mRadioButton2: RadioButton? = null
    private var mRadioButton3: RadioButton? = null
    private var mRadioButton4: RadioButton? = null
    private var mRadioButton5: RadioButton? = null
    private var mRadioButton6: RadioButton? = null
    private var mRadioButton7: RadioButton? = null


    constructor(context: Context) : super(context, R.style.DialogTheme) {
        initView(context)
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        initView(context)
    }


    private var convertView: View? = null

    private fun initView(mcontext: Context) {
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_dialog_chosefollow, null, false)
        mRadioGroup = convertView?.findViewById(R.id.radiogrop)
        mRadioButton1 = convertView?.findViewById(R.id.rb1)
        mRadioButton2 = convertView?.findViewById(R.id.rb2)
        mRadioButton3 = convertView?.findViewById(R.id.rb3)
        mRadioButton4 = convertView?.findViewById(R.id.rb4)
        mRadioButton5 = convertView?.findViewById(R.id.rb5)
        mRadioButton6 = convertView?.findViewById(R.id.rb6)
        mRadioButton7 = convertView?.findViewById(R.id.rb7)
        mRadioButton1?.setOnClickListener(this)
        mRadioButton2?.setOnClickListener(this)
        mRadioButton3?.setOnClickListener(this)
        mRadioButton4?.setOnClickListener(this)
        mRadioButton5?.setOnClickListener(this)
        mRadioButton6?.setOnClickListener(this)
        mRadioButton7?.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        dismiss()
        when (v?.id) {
            R.id.rb1 -> mOnclick!!.btndata("1")
            R.id.rb2 -> mOnclick!!.btndata("2")
            R.id.rb3 -> mOnclick!!.btndata("3")
            R.id.rb4 -> mOnclick!!.btndata("4")
            R.id.rb5 -> mOnclick!!.btndata("5")
            R.id.rb6 -> mOnclick!!.btndata("6")
            R.id.rb7 -> mOnclick!!.btndata("9")

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(convertView)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        val win = window
        val lp = win!!.attributes
        lp.gravity = Gravity.BOTTOM
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        win.attributes = lp
        win.setWindowAnimations(R.style.share_animation)

    }

    fun setBtnDataLinsenter(onclick: BtnDataLinsenter) {
        mOnclick = onclick
    }

    interface BtnDataLinsenter {
        fun btndata(str: String)
    }

}


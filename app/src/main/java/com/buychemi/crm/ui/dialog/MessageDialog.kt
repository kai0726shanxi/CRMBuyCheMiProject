package com.buychemi.crm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.buychemi.crm.R
import kotlinx.android.synthetic.main.layout_dialog_view.*

/**
 * @Author 20342
 * @Date 2019/8/14 9:30
 */
open class MessageDialog : Dialog {

    private var convertView: View? = null
    private var Ddialogonfirm:TextView?=null
    private var Ddialogclear:TextView?=null
    private var Ddialogmessage:TextView?=null
    private var Ddialogtitle:TextView?=null
    private var tv1:TextView?=null



    constructor(context: Context) : super(context, R.style.DialogTheme) {
        initView(context)
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        initView(context)
    }


    private fun initView(mcontext: Context) {
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_dialog_view, null, false)
        Ddialogonfirm=convertView?.findViewById(R.id.dialog_confirm)
        Ddialogclear=convertView?.findViewById(R.id.dialog_cancel)
        Ddialogmessage= convertView?.findViewById(R.id.dialog_message)
        Ddialogtitle= convertView?.findViewById(R.id.dialog_title)
        tv1=convertView?.findViewById(R.id.tv1)

    }

    //设置标题
    fun setTitle(title: String): MessageDialog {
        Ddialogtitle!!.text = title
        return this
    }

    //设置发送人数
    fun setcount(title: String): MessageDialog {
        tv1?.text = title
        return this
    }

    //设置内容
    fun setMessage(message: String): MessageDialog {
        dialog_message!!.text = message
        return this
    }

    fun setCancelText(cancel: String): MessageDialog {
        Ddialogclear!!.text = cancel
        return this
    }

    fun setConfirmText(confirm: String): MessageDialog {
        Ddialogonfirm!!.text = confirm
        return this
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(convertView)
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        val win = window
        val lp = win!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        win.attributes = lp
    }

    fun setOnClickListener(listener: View.OnClickListener): MessageDialog {

        Ddialogonfirm!!.setOnClickListener(listener)
        Ddialogclear!!.setOnClickListener(listener)

        return this
    }


}
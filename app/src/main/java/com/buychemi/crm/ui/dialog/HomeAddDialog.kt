package com.buychemi.crm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.buychemi.crm.R
import com.buychemi.crm.ui.adapter.AddDialogAdapter

/**
 *
 * @Author 20342
 * @Date 2019/8/14 15:54
 */
class HomeAddDialog : Dialog, View.OnClickListener {


    private val mlist = arrayListOf("1", "2", "4", "5", "6", "7")
    private var mAdapter: AddDialogAdapter? = null
    private var mOnclick: BtnDataLinsenter? = null
    private var mContext: Context? = null
    private var mRecycle: RecyclerView? = null
    private var mdelete: ImageView? = null

    constructor(context: Context) : super(context, R.style.DialogTheme) {
        mContext = context
        initView(context)
    }

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        mContext = context
        initView(context)
    }


    private var convertView: View? = null

    private fun initView(mcontext: Context) {
        convertView = LayoutInflater.from(mcontext).inflate(R.layout.layout_dialog_add, null, false)
        mRecycle = convertView?.findViewById(R.id.recycle_tab)
        mdelete = convertView?.findViewById(R.id.iv_delete)

        mAdapter = AddDialogAdapter(mcontext, mlist)
        mAdapter?.setOnItemClickListener { tag, i ->
            dismiss()
            mOnclick?.btndata(i.toString())


        }
        mRecycle?.adapter = mAdapter
        mRecycle?.layoutManager = GridLayoutManager(mcontext, 3)
        mdelete?.setOnClickListener {
            dismiss()
            mOnclick?.btndata("6")

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
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        win.attributes = lp
        win.setWindowAnimations(R.style.share_animation)
    }

    fun setBtnDataLinsenter(onclick: BtnDataLinsenter) {
        mOnclick = onclick
    }

    interface BtnDataLinsenter {
        fun btndata(str: String)
    }

    override fun onClick(v: View?) {
        dismiss()
        when (v?.id) {
            R.id.rb_1 -> {
                mOnclick!!.btndata("1")

            }
            R.id.rb_2 -> {
                mOnclick!!.btndata("2")

            }

        }

    }

}


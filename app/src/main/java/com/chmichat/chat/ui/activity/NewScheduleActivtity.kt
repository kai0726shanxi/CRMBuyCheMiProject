package com.chmichat.chat.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.hintKbTwo
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_new_schedule.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import java.text.SimpleDateFormat

/**
 * 新建日程
 * @Author 20342
 * @Date 2019/9/24 15:39
 */
class NewScheduleActivtity : BaseActivity(), View.OnClickListener {

    private var limit = 200
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    private var pvTime: TimePickerView? = null
    private var isStart:Boolean=false

    override fun layoutId(): Int {
        return R.layout.activity_new_schedule
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建日程"
        StatusBarUtil.darkMode(this)
        iv_left.setOnClickListener(this)
        tv_remind.setOnClickListener(this)
        tv_start.setOnClickListener(this)
        tv_end.setOnClickListener(this)
        edit_content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val after_length = s!!.length
                if (after_length > limit) {
                    val d_value = after_length - limit
                    val d_num = after_length - before_length
                    val st = cursor + (d_num - d_value)
                    val en = cursor + d_num
                    val s_new = s.delete(st, en)
                    edit_content.setText(s_new.toString())
                    edit_content.setSelection(st)
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                before_length = s!!.length

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cursor = start
                tvshownum.text = "已输入 ${s!!.length}/" + limit

            }


        })



        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, _ ->
            if (isStart){
                tv_start.text=SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

            }else{
                tv_end.text=SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

            }
        }).setDecorView(
                window.decorView.findViewById(android.R.id.content))
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setSubmitColor(ContextCompat.getColor(this, R.color.displaynomal))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.medynamic))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(this, R.color.displaynomal))
                .setType(booleanArrayOf(true,true,true,true,true,false))
                .setLabel("年","月","日","时","分","")                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setTitleBgColor(Color.WHITE)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()

    }

    override fun start() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> finish()
            R.id.tv_remind -> {
                var intent = Intent(this, RemindTimeActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, tv_remind.text.toString())
                startActivityForResult(intent,Constants.CHOSEFORUM)

            }
            R.id.tv_start->{
                isStart=true
                hintKbTwo(this)
                pvTime!!.show()
            }
            R.id.tv_end->{
                isStart=false
                hintKbTwo(this)
                pvTime!!.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CHOSEFORUM -> {
                    tv_remind.text= data?.getStringExtra(Constants.KEYTYPE)
                }
            }
        }

    }

}
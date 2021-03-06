package com.buychemi.crm.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.MessageEvent
import com.buychemi.crm.bean.UpImagesEntity
import com.buychemi.crm.hintKbTwo
import com.buychemi.crm.mvp.contract.NewScheduleContract
import com.buychemi.crm.mvp.presenter.NewSchedulePresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_new_schedule.*
import kotlinx.android.synthetic.main.title_bar_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat

/**
 * 新建日程
 * @Author 20342
 * @Date 2019/9/24 15:39
 */
class NewScheduleActivtity : BaseActivity(), NewScheduleContract.View, View.OnClickListener {


    private var limit = 200
    //  用来记录输入字符的时候光标的位置
    private var cursor = 0
    // 用来标注输入某一内容之前的编辑框中的内容的长度
    private var before_length: Int = 0
    private var pvTime: TimePickerView? = null
    private var isStart: Boolean = false
    private var isdata: String? = ""
    private var linkmanid: String = ""
    private var complayid: String = ""
    private var groupId: String = ""
    private var map = HashMap<String, String?>()
    private var mCheck: Int = 0
    private val mPresenter: NewSchedulePresenter by lazy { NewSchedulePresenter() }

    override fun layoutId(): Int {
        return R.layout.activity_new_schedule
    }

    override fun initData() {
        isdata = intent.getStringExtra(Constants.KEYNAME)
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建日程"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "新建"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        StatusBarUtil.darkMode(this)
        iv_left.setOnClickListener(this)
        tv_remind.setOnClickListener(this)
        tv_start.setOnClickListener(this)
        tv_right.setOnClickListener(this)
        tv_end.setOnClickListener(this)
        tv_customer.setOnClickListener(this)
        tv_customer.text = isdata

        radio_grop.setOnCheckedChangeListener { group, checkedId ->

            mCheck = checkedId
        }
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
            if (isStart) {
                tv_start.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

            } else {
                tv_end.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)

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
                .setType(booleanArrayOf(true, true, true, true, true, false))
                .setLabel("年", "月", "日", "时", "分", "").setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
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
                startActivityForResult(intent, Constants.CHOSEFORUM)

            }
            R.id.tv_start -> {
                isStart = true
                hintKbTwo(this)
                pvTime?.show()
            }
            R.id.tv_end -> {
                isStart = false
                hintKbTwo(this)
                pvTime!!.show()
            }
            R.id.tv_customer -> {
                var intent = Intent(this, MyGroupActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, 0)
                intent.putExtra(Constants.KEYTAG, 1)
                startActivity(intent)

            }
            R.id.tv_right -> {
                map.clear()
                if (et_title.text.toString() == "") {
                    showToast("请输入标题")
                    return
                }

                if (tv_start.text.toString() == "") {
                    showToast("请选择开始时间")
                    return
                }
                if (tv_end.text.toString() == "") {
                    showToast("请选择结束时间")
                    return
                }

                if (mCheck==0) {
                    showToast("请选择标记")
                    return
                }

                map["title"] = et_title.text.toString()
                map["startTime"] = tv_start.text.toString()
                map["endTime"] = tv_end.text.toString()
                map["groupId"] = groupId
                map["companyId"] = complayid
                map["customerId"] = linkmanid
                map["description"] = edit_content.text.toString()
                when (tv_remind.text.toString()) {
                    "准时" -> {
                        map["remindType"] = "0"
                    }
                    "提前5分钟" -> {
                        map["remindType"] = "1"
                    }
                    "提前10分钟" -> {
                        map["remindType"] = "2"
                    }
                }
                map["markValue"] = mCheck.toString()
                mPresenter.getNewSchedule(map)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CHOSEFORUM -> {
                    tv_remind.text = data?.getStringExtra(Constants.KEYTYPE)
                }
                Constants.CHOSUSER -> {
                    tv_customer.text = data?.getStringExtra(Constants.KEYNAME)

                }
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun Event(messageEvent: MessageEvent) {
        tv_customer.text = messageEvent.name
        linkmanid = messageEvent.linkmanId.toString()
        complayid = messageEvent.ComplayId.toString()
        groupId = messageEvent.GroupId.toString()
    }


    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onNewSchedule(data: String?) {
        showToast("创建成功")
        finish()
    }

    override fun onUpImages(data: ArrayList<UpImagesEntity>?) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}
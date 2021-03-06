package com.buychemi.crm.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.ui.adapter.RemindTimeAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_remind_time.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 提醒时间的选择
 * @Author 20342
 * @Date 2019/9/24 16:40
 */
class RemindTimeActivity : BaseActivity(), View.OnClickListener {


    private var mRemindTimeAdapter: RemindTimeAdapter? = null
    private val mlist = arrayListOf("不提醒", "准时", "提前5分钟", "提前10分钟")
    private var mStr:String?="不提醒"
    override fun layoutId(): Int {

        return R.layout.activity_remind_time
    }

    override fun initData() {
        mStr=intent.getStringExtra(Constants.KEYTYPE)
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.text = "提醒"
        mRemindTimeAdapter = RemindTimeAdapter(this, mlist)
        mRemindTimeAdapter?.mName= mStr.toString()
        mRemindTimeAdapter?.setOnItemClickListener { tag, i ->
            mRemindTimeAdapter?.mName = tag
            mRemindTimeAdapter?.notifyDataSetChanged()
            var intent = Intent()
            intent.putExtra(Constants.KEYTYPE, tag)
            setResult(Activity.RESULT_OK, intent)
            finish()


        }
        recyclerView.adapter = mRemindTimeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }
}
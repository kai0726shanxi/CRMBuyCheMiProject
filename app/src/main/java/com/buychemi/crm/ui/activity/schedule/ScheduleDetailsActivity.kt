package com.buychemi.crm.ui.activity.schedule

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.ui.adapter.schedule.ScheduleCommentAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_message_submit.*
import kotlinx.android.synthetic.main.activity_schedule_details.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 日程详情
 * @Author 20342
 * @Date 2019/10/16 16:15
 */
class ScheduleDetailsActivity : BaseActivity(),View.OnClickListener {
    private var mlist= arrayListOf("","","","","","","","","","","")
    private var mAdapter: ScheduleCommentAdapter?=null
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_left->{ finish() }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_schedule_details
    }

    override fun initData() {
    }

    override fun initView() {
        recyclerView.isNestedScrollingEnabled=false
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "日程详情"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mAdapter= ScheduleCommentAdapter(this,mlist)
        recyclerView.adapter=mAdapter
        recyclerView.layoutManager=LinearLayoutManager(this)


    }

    override fun start() {
    }
}
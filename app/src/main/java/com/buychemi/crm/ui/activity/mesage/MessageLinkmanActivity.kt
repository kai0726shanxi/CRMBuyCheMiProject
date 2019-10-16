package com.buychemi.crm.ui.activity.mesage

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.ui.adapter.MessageLinkmanAdapter
import kotlinx.android.synthetic.main.activity_my_message_linkman.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群发联系人
 * @Author 20342
 * @Date 2019/10/15 11:18
 */
class MessageLinkmanActivity:BaseActivity() {
    private var mAdapter: MessageLinkmanAdapter?=null
    private var mlist= arrayListOf<String>("1","","","","","","","","","","","","","","","","","")
    override fun layoutId(): Int {

       return R.layout.activity_my_message_linkman
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text="我的联系人"
        mAdapter= MessageLinkmanAdapter(this,mlist)
        recycler_view.adapter=mAdapter
        recycler_view.layoutManager=LinearLayoutManager(this)
    }

    override fun start() {
    }
}
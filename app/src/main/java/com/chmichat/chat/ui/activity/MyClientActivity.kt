package com.chmichat.chat.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.MyClientAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_client.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的客户
 * @Author 20342
 * @Date 2019/9/23 15:41
 */
class MyClientActivity:BaseActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_left->{
                finish()
            }
        }
    }
    private var mMyClientAdapter: MyClientAdapter?=null
    private val mlist= arrayListOf("1","","","","","","","","","","","","","","","","","","")
    override fun layoutId(): Int {
        return R.layout.activity_my_client
    }

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text="我的客户"
        iv_left.setOnClickListener(this)
        mMyClientAdapter= MyClientAdapter(this,mlist)
        recycler_view.adapter=mMyClientAdapter
        recycler_view.layoutManager=LinearLayoutManager(this)

    }

    override fun start() {
    }
}
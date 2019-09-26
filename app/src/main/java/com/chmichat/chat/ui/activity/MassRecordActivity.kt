package com.chmichat.chat.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.MassRecordAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_massrecord.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群发记录（群发信息）
 * @Author 20342
 * @Date 2019/9/26 10:44
 */
class MassRecordActivity : BaseActivity(), View.OnClickListener {
    private val mlist = arrayListOf("", "", "", "", "")
    private var mMassRecordAdapter: MassRecordAdapter? = null
    override fun layoutId(): Int {
        return R.layout.activity_massrecord
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.text = "群发记录"
        StatusBarUtil.darkMode(this)
        mMassRecordAdapter = MassRecordAdapter(this, mlist)

        recyclerView.adapter = mMassRecordAdapter
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
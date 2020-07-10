package com.buychemi.crm.ui.activity.mesage

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.ui.adapter.GroupsentAdapter
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_groupsent_message.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群组列表
 * @Author 20342
 * @Date 2019/9/24 18:41
 */
class GroupSentMessageActivity : BaseActivity() ,View.OnClickListener{

    private var mGroupsentAdapter: GroupsentAdapter? = null
    private val mlist = arrayListOf("发短信给联系人", "发短信给客户组")
    override fun layoutId(): Int {
        return R.layout.activity_groupsent_message
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text="群组消息"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mGroupsentAdapter = GroupsentAdapter(this, mlist)
        recyclerView.adapter = mGroupsentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
when(v?.id){
    R.id.iv_left->finish()



}    }

}
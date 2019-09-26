package com.chmichat.chat.ui.activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.ui.fragment.MyClientTabFragment
import com.chmichat.chat.ui.fragment.MyLinkManTabFragment
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_myclientdetails.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的客户详情
 * @Author 20342
 * @Date 2019/9/23 17:07
 */
class MyClientDetailsActivity : BaseActivity(), View.OnClickListener {


    private var mlist = arrayListOf("跟进记录", "基本信息", "相关信息")
    private val mFragmentList = ArrayList<Fragment>()
    private var mType:String="0"

    override fun layoutId(): Int {
        return R.layout.activity_myclientdetails
    }

    override fun initData() {
        mType=intent.getStringExtra(Constants.KEYTYPE)
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, appbar_layout)
        iv_left.setOnClickListener(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        if (mType=="0"){
            tv_title.text = "客户详情"
            tv_company.visibility=View.GONE


        }else{
            tv_title.text = "联系人详情"
            tv_company.visibility=View.VISIBLE

        }

        mFragmentList.add(MyClientTabFragment.getInstance(mType,"0"))
        mFragmentList.add(MyClientTabFragment.getInstance(mType,"1"))
        mFragmentList.add(MyClientTabFragment.getInstance(mType,"2"))
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mlist)
        tab_layout.setViewPager(mViewPager)

    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
    R.id.iv_left->{
        finish()
    }
        }
    }

}
package com.chmichat.chat.ui.activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.base.BaseFragmentAdapter
import com.chmichat.chat.ui.fragment.MyLinkManTabFragment
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_group.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:15
 */
class MyLinkManActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    private val mFragmentList = ArrayList<Fragment>()
    private var mTitles = arrayListOf("我负责的", "其他的")
    override fun layoutId(): Int {
        return R.layout.activity_my_group
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的联系人"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        mFragmentList.add(MyLinkManTabFragment.getInstance("1"))
        mFragmentList.add(MyLinkManTabFragment.getInstance("2"))
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTitles)
        tab_layout.setViewPager(mViewPager)
    }

    override fun start() {
    }
}
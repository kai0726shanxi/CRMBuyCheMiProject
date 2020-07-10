package com.buychemi.crm.ui.activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.base.BaseFragmentAdapter
import com.buychemi.crm.showToast
import com.buychemi.crm.ui.fragment.MyGroupTabFragment
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_group.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的分组
 * @Author 20342
 * @Date 2019/9/23 14:15
 */
class MyGroupActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
        }
    }

    private val mFragmentList = ArrayList<Fragment>()
    private var mTitles = arrayListOf("我的管理", "我的下属")
    private var mType: Int = 0
    private var mTag: Int = 0

    override fun layoutId(): Int {
        return R.layout.activity_my_new_group
    }

    override fun initData() {
        mType = intent.getIntExtra(Constants.KEYTYPE, 0)
        mTag = intent.getIntExtra(Constants.KEYTAG, 0)
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的分组"
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        if (mType != 0) {
            tab_layout.visibility = View.VISIBLE
            mFragmentList.add(MyGroupTabFragment.getInstance("1", false, "0"))
            mFragmentList.add(MyGroupTabFragment.getInstance("2", false, "0"))
            mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTitles)
            tab_layout.setViewPager(mViewPager)
            mViewPager.setCurrentItem(mType, true)


        } else {
            if (mTag == 0) {
                tab_layout.visibility = View.VISIBLE
                mFragmentList.add(MyGroupTabFragment.getInstance("1", false, "0"))
                mFragmentList.add(MyGroupTabFragment.getInstance("2", false, "0"))
                mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTitles)
                tab_layout.setViewPager(mViewPager)
                mViewPager.setCurrentItem(mType, true)
            } else {
                tv_title.text="关联客户"
                tab_layout.visibility = View.GONE
                mFragmentList.add(MyGroupTabFragment.getInstance("1", true, mTag.toString()))
                mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTitles)
                tab_layout.setViewPager(mViewPager)
                mViewPager.setCurrentItem(0, true)
            }


        }

    }

    override fun start() {
    }


}
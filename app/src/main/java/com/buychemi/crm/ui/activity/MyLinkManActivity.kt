package com.buychemi.crm.ui.activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.base.BaseFragmentAdapter
import com.buychemi.crm.ui.fragment.MyLinkManTabFragment
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_group.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/9/23 14:15
 */
class MyLinkManActivity : BaseActivity(), View.OnClickListener {
    private var mType: String? = "0"//区分从哪里跳来
    private var mgroupId:Int?=0
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
        mType = intent.getStringExtra(Constants.KEYNAME)
        mgroupId=intent.getIntExtra(Constants.KEYGROUPID,0)
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "我的客户"
        tab_layout.visibility=View.GONE
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        if (mType=="1"){
            tv_title.text = "关联客户"

            tv_num_bottom.visibility=View.GONE
            tv_btn.visibility=View.GONE
        }else{
            tv_num_bottom.visibility=View.GONE
            tv_btn.visibility=View.GONE
        }

        mFragmentList.add(MyLinkManTabFragment.getInstance("1", mType,mgroupId))
      //  mFragmentList.add(MyLinkManTabFragment.getInstance("2", mType,mgroupId))
        mViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragmentList, mTitles)
        tab_layout.setViewPager(mViewPager)
    }

    override fun start() {
    }
}
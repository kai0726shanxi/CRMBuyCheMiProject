package com.chmichat.chat.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.ui.adapter.PotentialCustomerAdapter
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_potentialcustomer.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 潜在客户
 * @Author 20342
 * @Date 2019/9/26 10:08
 */
class PotentialCustomerActivity : BaseActivity(),View.OnClickListener {

    private val mlist = arrayListOf("", "", "", "", "", "", "", "", "", "", "", "")
    private var mPotentialCustomerAdapter: PotentialCustomerAdapter? = null
    private var mType :String?="0"
    override fun layoutId(): Int {

        return R.layout.activity_potentialcustomer
    }

    override fun initData() {
        mType=intent.getStringExtra(Constants.KEYTYPE)
    }

    override fun initView() {
        iv_left.visibility=View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,cl_bar)
        if (mType=="1"){
            tv_title.text="发送记录"

        }else{
            tv_title.text="潜在客户"

        }
        iv_left.setOnClickListener(this)
        mPotentialCustomerAdapter = PotentialCustomerAdapter(this, mlist)
        recycler_view.adapter = mPotentialCustomerAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_left->{
                finish()
            }
        }
    }

}
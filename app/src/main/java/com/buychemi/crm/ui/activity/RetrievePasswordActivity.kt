package com.buychemi.crm.ui.activity

import android.graphics.Color
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/10/15 15:47
 */
class RetrievePasswordActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_possword_layout
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "找回密码"
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
    }

    override fun start() {
    }
}
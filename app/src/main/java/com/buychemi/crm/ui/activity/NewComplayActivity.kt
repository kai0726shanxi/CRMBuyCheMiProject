package com.buychemi.crm.ui.activity

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.showToast
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_newcontact.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 新建公司
 * @Author 20342
 * @Date 2019/9/26 14:35
 */
class NewComplayActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_right -> {

                showToast("创建")
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_newcomplay
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建公司"
        tv_right.visibility = View.VISIBLE
        iv_left.setOnClickListener(this)
        tv_right.setOnClickListener(this)

        tv_right.text="创建"
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)

    }

    override fun start() {
    }
}
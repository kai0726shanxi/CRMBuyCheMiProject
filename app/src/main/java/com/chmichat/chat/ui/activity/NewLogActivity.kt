package com.chmichat.chat.ui.activity

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseActivity
import com.chmichat.chat.showToast
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 新建日志
 * @Author 20342
 * @Date 2019/9/26 13:45
 */
class NewLogActivity : BaseActivity(), View.OnClickListener {


    override fun layoutId(): Int {
        return R.layout.activity_new_log
    }

    override fun initData() {
    }

    override fun initView() {
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        tv_title.text = "新建备忘"
        tv_right.visibility = View.VISIBLE
        tv_right.text = "保存"
        tv_right.setOnClickListener(this)
        iv_left.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_right.setTextColor(ContextCompat.getColor(this, R.color.home_bottom_down))

    }

    override fun start() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_right -> {
                showToast("保存")
            }
            R.id.iv_left -> {
                finish()
            }

        }
    }
}
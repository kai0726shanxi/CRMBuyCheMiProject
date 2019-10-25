package com.buychemi.crm.ui.activity.mesage

import android.content.Intent
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.ui.activity.MainActivity
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_message_submit.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * 群发信息提交界面
 * @Author 20342
 * @Date 2019/10/15 10:15
 */
class MessageSubmintActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_cancel
            -> {
                var intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            R.id.tv_look -> {
                var intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra(Constants.KEYTYPE,"LIST")//进入发送记录
                startActivity(intent)
                finish()


            }

        }
    }


    override fun layoutId(): Int {
        return R.layout.activity_message_submit
    }

    override fun initData() {
    }

    override fun initView() {
        tv_title.text = "群发提交"
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
        tv_cancel.setOnClickListener(this)
        tv_look.setOnClickListener(this)
    }

    override fun start() {
    }
}
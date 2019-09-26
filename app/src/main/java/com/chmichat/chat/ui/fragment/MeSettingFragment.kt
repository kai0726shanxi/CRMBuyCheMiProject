package com.chmichat.chat.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chmichat.chat.Constants
import com.chmichat.chat.R
import com.chmichat.chat.base.BaseFragment
import com.chmichat.chat.ui.activity.MyGroupActivity
import com.chmichat.chat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_me_layout.*

/**
 * 我的界面
 * @Author 20342
 * @Date 2019/9/21 12:58
 */
class MeSettingFragment : BaseFragment(), View.OnClickListener {


    companion object {
        fun getInstance(): MeSettingFragment {
            val fragment = MeSettingFragment()
            val bundle = Bundle()
            fragment.arguments = bundle

            return fragment

        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_me_layout

    override fun initView() {
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, cl_bar_new) }

        tv_content_left.setOnClickListener(this)
        tv_content_right.setOnClickListener(this)
    }


    override fun lazyLoad() {
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.tv_content_left->{
             var intent=Intent(activity,MyGroupActivity::class.java)
                intent.putExtra(Constants.KEYTYPE,0)
                startActivity(intent)
            }
            R.id.tv_content_right->{
                var intent=Intent(activity,MyGroupActivity::class.java)
                intent.putExtra(Constants.KEYTYPE,1)

                startActivity(intent)
            }


        }
    }
}
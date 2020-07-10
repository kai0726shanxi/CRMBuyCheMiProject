package com.buychemi.crm.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseFragment
import com.buychemi.crm.bean.UserBean
import com.buychemi.crm.ui.activity.LogInActivity
import com.buychemi.crm.ui.activity.MyGroupActivity
import com.buychemi.crm.ui.activity.RetrievePasswordActivity
import com.buychemi.crm.ui.activity.ShowContentActivity
import com.buychemi.crm.utils.SpUtil
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_me_layout.*

/**
 * 我的界面
 * @Author 20342
 * @Date 2019/9/21 12:58
 */
class MeSettingFragment : BaseFragment(), View.OnClickListener {

    private var userdata: UserBean? = null

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
        tv_login.setOnClickListener(this)
        tv_content_left.setOnClickListener(this)
        tv_content_right.setOnClickListener(this)
        tv_psw.setOnClickListener(this)
        tv_about.setOnClickListener(this)
    }


    override fun lazyLoad() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_content_left -> {
                var intent = Intent(activity, MyGroupActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, 0)
                startActivity(intent)
            }
            R.id.tv_content_right -> {
                var intent = Intent(activity, MyGroupActivity::class.java)
                intent.putExtra(Constants.KEYTYPE, 1)

                startActivity(intent)
            }
            R.id.tv_login -> {
                SpUtil.cleardata(activity)
                var intent = Intent(activity, LogInActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_psw -> {
                startActivity(Intent(activity, RetrievePasswordActivity::class.java))

            }
            R.id.tv_about->{
                startActivity(Intent(activity,ShowContentActivity::class.java))
            }


        }
    }

    override fun onStart() {
        super.onStart()
        userdata = SpUtil.getObject(activity, Constants.USERBEAN)
        if (userdata != null) {
            tv_name.text = userdata?.name
            tv_post.text = userdata?.position
            tv_company.text = userdata?.companyName
        }
    }
}
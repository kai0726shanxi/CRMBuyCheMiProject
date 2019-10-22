package com.buychemi.crm.ui.activity

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.UserBean
import com.buychemi.crm.mvp.contract.LoginContract
import com.buychemi.crm.mvp.presenter.LoginPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_message_submit.*
import kotlinx.android.synthetic.main.activity_possword_layout.*
import kotlinx.android.synthetic.main.title_bar_layout.*

/**
 * @Author 20342
 * @Date 2019/10/15 15:47
 */
class RetrievePasswordActivity : BaseActivity(), LoginContract.View, View.OnClickListener {

    private val mPresenter: LoginPresenter by lazy { LoginPresenter() }
    private var map = HashMap<String, String>()
    override fun layoutId(): Int {
        return R.layout.activity_possword_layout
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        iv_left.visibility = View.VISIBLE
        iv_left.setColorFilter(Color.BLACK)
        iv_left.setOnClickListener(this)
        tv_title.text = "找回密码"
        tv_send.setOnClickListener(this)
        tv_login.setOnClickListener(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, cl_bar)
    }

    override fun start() {
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onloginMember(data: UserBean?) {

    }

    override fun onFindpsw(data: String?) {
        showToast("密码修改成功")

        var intent = Intent(this, LogInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun onSendCode(data: String?) {
        showToast("验证码发送成功")
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_left -> {
                finish()
            }
            R.id.tv_send -> {
                if (et_phone.text.toString() == "") {
                    showToast("请输入手机号")

                    return
                }
                map.clear()
                map["mobile"] = et_phone.text.toString()
                mPresenter.getSendCode(map)

            }
            R.id.tv_login -> {
                if (et_phone.text.toString() == "") {
                    showToast("请输入手机号")

                    return
                }
                if (et_code.text.toString() == "") {
                    showToast("请输入手机号")

                    return
                }
                if (et_psw.text.toString() == "") {
                    showToast("请输入手机号")

                    return
                }

                map.clear()
                map["mobile"] = et_phone.text.toString()
                map["smsCode"] = et_code.text.toString()
                map["psw"] = et_psw.text.toString()


                mPresenter.getFindPsw(map)

            }
        }
    }
}
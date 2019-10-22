package com.buychemi.crm.ui.activity

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import com.buychemi.crm.Constants
import com.buychemi.crm.R
import com.buychemi.crm.base.BaseActivity
import com.buychemi.crm.bean.UserBean
import com.buychemi.crm.mvp.contract.LoginContract
import com.buychemi.crm.mvp.presenter.LoginPresenter
import com.buychemi.crm.showToast
import com.buychemi.crm.utils.SpUtil
import com.buychemi.crm.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login_layout.*

/**
 * @Author 20342
 * @Date 2019/10/15 15:10
 */
class LogInActivity : BaseActivity(), View.OnClickListener, LoginContract.View {
    override fun onFindpsw(data: String?) {

    }

    override fun onSendCode(data: String?) {
    }

    private var map=HashMap<String,String>()
    private val mPresenter: LoginPresenter by lazy { LoginPresenter() }
    override fun layoutId(): Int {

        return R.layout.activity_login_layout
    }

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        tv_forgetpsw.setOnClickListener(this)
        tv_login.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ShowErrorMes(errorMsg, errorCode)
    }

    override fun onloginMember(data: UserBean?) {
        SpUtil.putObject(this,Constants.USERBEAN,data)
        SpUtil.putString(this,Constants.USERBEANTOKEN,data?.token)

        val intent=Intent(this,MainActivity::class.java)
         intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_forgetpsw -> {
                startActivity(Intent(this, RetrievePasswordActivity::class.java))
            }

            R.id.tv_login -> {
             map.clear()
             map["telPhone"]=et_phone.text.toString().trim()
             map["pwd"]=et_psw.text.toString().trim()
             mPresenter.getLoginMember(map)

            }
        }
    }
    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")

            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
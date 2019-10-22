package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.LoginContract
import com.buychemi.crm.mvp.model.LoginModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {



    private val loginModel: LoginModel by lazy {
        LoginModel()
    }

    override fun getLoginMember(map: Map<String, String>) {
        //邮箱发送验证码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getLoginMember(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onloginMember(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }

    override fun getFindPsw(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getFindPsw(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onFindpsw(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }
    //发送验证
    override fun getSendCode(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getSendPsw(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onSendCode(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }
}
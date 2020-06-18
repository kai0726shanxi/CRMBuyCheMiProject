package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.LogDetailsContract
import com.buychemi.crm.mvp.contract.LoginContract
import com.buychemi.crm.mvp.model.LogDetailsModel
import com.buychemi.crm.mvp.model.LoginModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class LogDetailsPresenter : BasePresenter<LogDetailsContract.View>(), LogDetailsContract.Presenter {


    private val loginModel: LogDetailsModel by lazy {
        LogDetailsModel()
    }

    override fun getFollowDetails(map: Map<String, String>) {
        //邮箱发送验证码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getFollowDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onFollowDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }

    override fun getLogDetails(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getLogdetaila(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onLogDetails(data.data)
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
package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.LogBookContract
import com.buychemi.crm.mvp.model.LogLookModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class LogLookPresenter:BasePresenter<LogBookContract.View>(),LogBookContract.Presenter {
    override fun getMyLogCalender(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getCalendarList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyLogCalender(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)     }


    private val loginModel: LogLookModel by lazy {
        LogLookModel()
    }
    override fun getMyLogList(map: Map<String, String>) {
        //邮箱发送验证码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getLogList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onMyLogList(data.data,data.pageCount)
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
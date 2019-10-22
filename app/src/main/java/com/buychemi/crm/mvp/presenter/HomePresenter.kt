package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.HomeContract
import com.buychemi.crm.mvp.model.HomeModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class HomePresenter:BasePresenter<HomeContract.View>(),HomeContract.Presenter {


    private val loginModel: HomeModel by lazy {
        HomeModel()
    }
    override fun getUserLogList(map: Map<String, String>) {
        //日志列表，首页
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getuserLogList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onUserLogList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }

    override fun getBriefing(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getBriefing(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onBriefing(data.data)
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
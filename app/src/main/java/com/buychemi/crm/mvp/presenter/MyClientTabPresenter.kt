package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.MyClientTabContract
import com.buychemi.crm.mvp.model.MyClientTabModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/21 9:53
 */
class MyClientTabPresenter:BasePresenter<MyClientTabContract.View>(),MyClientTabContract.Presenter{
    override fun onComPlanyCustomer(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getComPlanyCustomer(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onComPlanyCustomer(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)     }


    override fun getUserLogList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getLinkLogList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onLinkLogList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }

    private val mModel: MyClientTabModel by lazy { MyClientTabModel() }
       //获取联系人跟进列表
    override fun getFollowList(map: Map<String, String>) {
            checkViewAttached()
            mRootView?.showLoading()

            val disposable = mModel.getFollowList(map)
                    .subscribe({ data ->
                        mRootView?.apply {
                            dismissLoading()
                            onFollowList(data.data,data.pageCount)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    })
            addSubscription(disposable)
        }

    override fun getLinkLogList(map: Map<String, String>) {
//获取用户的日志列表（调用此接口）
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getLinkLogList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onLinkLogList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getComplayLogList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getComplayLogList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onComplayLogList(data.data,data.pageCount)
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
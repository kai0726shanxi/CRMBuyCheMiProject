package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.bean.PostBodyEntity
import com.buychemi.crm.mvp.contract.NewMemoContract
import com.buychemi.crm.mvp.model.NewMemoModel
import com.buychemi.crm.net.exception.ExceptionHandle
import okhttp3.RequestBody

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class NewMemoPresenter:BasePresenter<NewMemoContract.View>(),NewMemoContract.Presenter {

    private val mModel: NewMemoModel by lazy {
        NewMemoModel()
    }
    override fun getNewMemodata(map: Map<String,Any>) {
        //新建报告
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getNewMemo(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onNewMemodata(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }
    override fun getUpImages(body:RequestBody) {
        //新建报告
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getUpImages(body)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onUpImages(data.data)
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
package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.MassRecordContract
import com.buychemi.crm.mvp.model.MassRecordModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * Created by 20342 on 2019-10-22
 */
class MassRecordPresenter : BasePresenter<MassRecordContract.View>(), MassRecordContract.Presenter {
    override fun getSendLinkList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getSendLinkList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        ontSendLinkList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)     }


    private val mModel: MassRecordModel by lazy { MassRecordModel() }
    override fun getSendFroupList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getSendGroupList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onSendGroupList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }
}
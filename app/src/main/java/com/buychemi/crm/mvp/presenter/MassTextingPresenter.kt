package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.MassTextingContract
import com.buychemi.crm.mvp.model.MassTextingModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/21 19:28
 */
class MassTextingPresenter : BasePresenter<MassTextingContract.View>(), MassTextingContract.Presenter {
    private val mModel: MassTextingModel by lazy { MassTextingModel() }
    override fun getsendmessage(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getSendMessage(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onsendMessage(data.data)
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
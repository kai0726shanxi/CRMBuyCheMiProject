package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.TemplateContract
import com.buychemi.crm.mvp.model.TemplateModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/21 17:38
 */
class TemplatePresenter : BasePresenter<TemplateContract.View>(), TemplateContract.Presenter {


    private val mModel: TemplateModel by lazy { TemplateModel() }
    override fun getTemplateList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getTemplatList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onTemplateList(data.data, data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getGroupids(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getGroupids(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onGroupids(data.data)
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
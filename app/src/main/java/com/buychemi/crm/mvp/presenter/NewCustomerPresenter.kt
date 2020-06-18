package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.NewCustomerContract
import com.buychemi.crm.mvp.model.NewCustomerModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/18 10:51
 */
class NewCustomerPresenter:BasePresenter<NewCustomerContract.View>(),NewCustomerContract.Presenter {
    override fun getMyGroupData(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getMyGroupData(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyGroupData(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)      }

    override fun getNewAddCustomer(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getNewAddCustomer(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onNewAddCustomer(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)     }

    override fun getcompanyList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getComplanyList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        oncompanyList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)     }

/*
    override fun getCompanlyDetails(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getCompanlyDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCompanlyDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)      }*/




    private val mModel: NewCustomerModel by lazy { NewCustomerModel() }


}
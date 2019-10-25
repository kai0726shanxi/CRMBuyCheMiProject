package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.CustomerContract
import com.buychemi.crm.mvp.contract.GrouptabContract
import com.buychemi.crm.mvp.model.CustomerModel
import com.buychemi.crm.mvp.model.GroupTabModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/18 10:51
 */
class CustomerPresenter:BasePresenter<CustomerContract.View>(),CustomerContract.Presenter {


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
        addSubscription(disposable)      }

    override fun getCustomerDetails(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getCustomerDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onCustomerDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }


    private val mModel: CustomerModel by lazy { CustomerModel() }

    override fun getMyCustomerlist(map: Map<String, String>) {

        //邮箱发送验证码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getMyGroupList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyCustomerlist(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)

    }

    override fun getHomeCustomer(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getHomecustimer(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onHomeCustomer(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }
}
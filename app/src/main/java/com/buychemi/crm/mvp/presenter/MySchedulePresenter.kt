package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.LogBookContract
import com.buychemi.crm.mvp.contract.MyScheduleContract
import com.buychemi.crm.mvp.model.LogLookModel
import com.buychemi.crm.mvp.model.MyScheduleModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class MySchedulePresenter : BasePresenter<MyScheduleContract.View>(), MyScheduleContract.Presenter {


    override fun getmanagementDetails(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getmanagementDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        ontmanagementDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getMyscheduleList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getCalendarList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyscheduleCalender(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getMyscheduleCalender(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getManagementList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyscheduleList(data.data, data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    private val loginModel: MyScheduleModel by lazy {
        MyScheduleModel()
    }

    override fun getMyscheduleOver(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getMyscheduleOver(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyscheduleOver(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getMyscheduledelete(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getMyscheduleDelete(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyscheduledelete(data.data)
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
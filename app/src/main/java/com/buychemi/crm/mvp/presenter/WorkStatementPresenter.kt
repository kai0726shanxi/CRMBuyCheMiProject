package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.WorkStatementContract
import com.buychemi.crm.mvp.model.WorkStatementModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/21 13:58
 */
class WorkStatementPresenter : BasePresenter<WorkStatementContract.View>(), WorkStatementContract.Presenter {
    override fun getreportadd(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getReportadd(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onreportadd(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    private val mModel: WorkStatementModel by lazy { WorkStatementModel() }
    //我的报告列表
    override fun getReportList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getReportList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onReportList(data.data, data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //报告详情
    override fun getRePortDetails(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getReportDetails(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onRePortDetails(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    //详情评论列表
    override fun getRePortComment(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getReportCommentList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onReportComment(data.data, data.pageCount)
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
package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.FollowUpRecordsContract
import com.buychemi.crm.mvp.model.FollowUpRecordsModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/15 16:22
 */
class FollowUpRecordsPresenter : BasePresenter<FollowUpRecordsContract.View>(), FollowUpRecordsContract.Presenter {
    override fun getMyFollowCalender(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getCalendarList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyFollowCalender(data.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }


    private val loginModel: FollowUpRecordsModel by lazy {
        FollowUpRecordsModel()
    }

    override fun getMyFollowList(map: Map<String, String>) {
        //邮箱发送验证码
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.getFollowList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()

                        onMyFollowList(data.data, data.pageCount)
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
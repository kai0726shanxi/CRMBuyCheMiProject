package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.MySubordinateContract
import com.buychemi.crm.mvp.model.MySubordinateModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/24 18:23
 */
class MySubordinatePresenter:BasePresenter<MySubordinateContract.View>(),MySubordinateContract.Presenter {


    private val mModel: MySubordinateModel by lazy { MySubordinateModel() }

    override fun getMySubordinatelist(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getMySubordinateList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMySubordinatelist(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun getFindCustomerList(map: Map<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getFindCustomerList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onFindCustomerList(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)    }


}
package com.buychemi.crm.mvp.presenter

import com.buychemi.crm.base.BasePresenter
import com.buychemi.crm.mvp.contract.GrouptabContract
import com.buychemi.crm.mvp.model.GroupTabModel
import com.buychemi.crm.net.exception.ExceptionHandle

/**
 * @Author 20342
 * @Date 2019/10/18 10:51
 */
class GroupTabPresenter:BasePresenter<GrouptabContract.View>(),GrouptabContract.Presenter {
    override fun getSubordinatelist(map: Map<String, String>) {
//我下属的分组
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getSubordinatelist(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onSubordinatelist(data.data,data.pageCount)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
   }


    private val mModel:GroupTabModel by lazy { GroupTabModel() }

    override fun getMyGrouplist(map: Map<String, String>) {

        //我的分组
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = mModel.getMyGroupList(map)
                .subscribe({ data ->
                    mRootView?.apply {
                        dismissLoading()
                        onMyGrouplist(data.data,data.pageCount)
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
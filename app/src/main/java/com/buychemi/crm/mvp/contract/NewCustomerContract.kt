package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.*

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface NewCustomerContract {

    interface View : IBaseView {
        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

        fun onNewAddCustomer(data: LinkTestEntity?)
        fun oncompanyList(data: ArrayList<CompanyListEntity>?,total:Int)
        fun onMyGroupData(data: ArrayList<GroupListEntity>?,total:Int)


    }

    interface Presenter {
        fun getNewAddCustomer(map: Map<String, String>)
        fun getcompanyList(map: Map<String, String>)
        fun getMyGroupData(map: Map<String, String>)

    }
}
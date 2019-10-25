package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.MyStaffEntity

/**
 * @Author 20342
 * @Date 2019/10/24 18:21
 */
interface MySubordinateContract {
    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun onMySubordinatelist(data: ArrayList<MyStaffEntity>?, total:Int?)
        fun onFindCustomerList(data: ArrayList<CustomerListEntity>?, total:Int?)

    }
    interface Presenter{
        fun getMySubordinatelist(map: Map<String,String>)
        fun getFindCustomerList(map: Map<String,String>)

    }
}
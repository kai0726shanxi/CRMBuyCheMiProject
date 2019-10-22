package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.CompanyDetailsEntity
import com.buychemi.crm.bean.CustomerDetailsEntity
import com.buychemi.crm.bean.CustomerListEntity

/**
 * 我的联系人
 * @Author 20342
 * @Date 2019/10/15 16:18
 */
interface CustomerContract {

  interface View:IBaseView{
      /**
       * 显示错误信息
       */
      fun showError(errorMsg:String,errorCode:Int)
      /**
       * 我的分组数据
       *
       */
      fun onMyCustomerlist(data: ArrayList<CustomerListEntity>?, total:Int?)
      fun onCustomerDetails(data: CustomerDetailsEntity?)
      fun onCompanlyDetails(data: CompanyDetailsEntity?)
  }
    interface Presenter{
        fun getMyCustomerlist(map:Map<String,String>)
        fun getCustomerDetails(map:Map<String,String>)
        fun getCompanlyDetails(map:Map<String,String>)
    }
}
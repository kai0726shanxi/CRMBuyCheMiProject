package com.buychemi.crm.mvp.contract

import com.buychemi.crm.base.IBaseView
import com.buychemi.crm.bean.CustomerListEntity
import com.buychemi.crm.bean.FollowDetailsEntity
import com.buychemi.crm.bean.LogDetailsEntity
import com.buychemi.crm.bean.MyStaffEntity

/**
 * @Author 20342
 * @Date 2019/10/24 18:21
 */
interface LogDetailsContract {
    interface View:IBaseView{
        fun showError(errorMsg:String,errorCode:Int)
        fun onFollowDetails(data:FollowDetailsEntity?)
        fun onLogDetails(data: LogDetailsEntity?)

    }
    interface Presenter{
        fun getFollowDetails(map: Map<String,String>)
        fun getLogDetails(map: Map<String,String>)

    }
}